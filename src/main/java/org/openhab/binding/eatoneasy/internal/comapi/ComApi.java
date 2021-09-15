package org.openhab.binding.eatoneasy.internal.comapi;

import java.io.IOException;

import com.sun.jna.Native;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class ComApi {
    private static DLLinterfaceMC lib;
    private static ComApi[] comPorts = new ComApi[256];

    public static enum DeviceType {
        EASY_500_700,
        EASY_800_MFD
    }

    public static enum BaudRate {
        BR_4800(4800),
        BR_9600(9600),
        BR_19200(19200),
        BR_38400(38400),
        BR_57600(57600);

        private int br;

        BaudRate(int br) {
            this.br = br;
        }

        public int val() {
            return br;
        }
    }

    public static enum ElementType {
        DIGITAL_INPUT__I1_I16(0),
        DIGITAL_OUTPUT__Q1_Q8(1),
        DIGITAL_INPUT_EXP__R1_R16(2),
        DIGITAL_OUTPUT_EXP__S1_S8(3),
        BIT_MARKER__M1_M96(4),
        P_BUTTONS(5),
        ANALOG_INPUT__AI1_AI4(8),
        ANALOG_OUTPUT__QA1(9),
        UNDEFINED(255);

        private byte c;

        ElementType(int c) {
            this.c = (byte) c;
        }

        public byte val() {
            return c;
        }
    }

    public static enum ErrorType {
        DEVICE_EXISTS,
        DEVICE_TYPE,
        TIMEOUT,
        PORT,
        NETID,
        MARKER_RANGE,
        ELEMENT_TYPE,
        NO_PASSWORD
    }

    private PointerByReference pHandle = new PointerByReference();
    private DeviceType[] pDevices = new DeviceType[9];
    private byte pComPort;
    private int pBaudRate;

    private ComApi(int comPort, BaudRate baudRate) throws IllegalArgumentException {

        if (comPort < 0 || comPort > 255) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.PORT));
        }
        this.pComPort = (byte) comPort;
        this.pBaudRate = baudRate.val();
    }

    private synchronized static void initializeDLL(int timeout) throws UnsatisfiedLinkError {

        if (lib == null) {
            lib = Native.load("EasyDLL/EASY_COM", DLLinterfaceMC.class);

            setTimeout(timeout);
        }
    }

    private static void setTimeout(int timeout) throws IllegalArgumentException {

        if (lib.MC_Set_UserWaitingTime(timeout) > 0) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.TIMEOUT));
        }
    }

    @SuppressWarnings("unused")
    private static int getTimeout() throws IllegalArgumentException {

        IntByReference pWord = new IntByReference(0);

        if (lib.MC_Get_UserWaitingTime(pWord) > 0) {
            throw new IllegalArgumentException(libErrorCode(1, 0, null));
        }

        return pWord.getValue();
    }

    /**
     *
     * @param deviceType Link to device. Is used to close COM after the last device disconnected
     * @param comPort Number of COM-port to connect
     * @param baudRate Speed of communication. Default 19200
     * @param timeout Timeout for successful connection. Default - 800 ms
     * @param netId Used to track connected devices to this COM-port
     * @throws IllegalArgumentException
     * @throws UnsatisfiedLinkError
     * @throws IOException
     */
    public synchronized static ComApi connect(DeviceType deviceType, int comPort, BaudRate baudRate, int timeout,
            int netId, String password) throws IllegalArgumentException, UnsatisfiedLinkError, IOException {
        if (deviceType == null) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.DEVICE_TYPE));
        }
        if (comPort < 0 || comPort > 255) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.PORT));
        }
        if (netId < 0 || netId > 8) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.NETID));
        }

        if (comPorts[comPort] != null) {
            if (comPorts[comPort].pDevices[netId] == null) {
                comPorts[comPort].pDevices[netId] = deviceType;
                return comPorts[comPort];
            } else {
                throw new IllegalArgumentException(packageErrorCode(ErrorType.DEVICE_EXISTS));
            }
        } else {
            if (timeout < 0 || timeout > 90000) {
                throw new IllegalArgumentException(packageErrorCode(ErrorType.TIMEOUT));
            }

            ComApi.initializeDLL(timeout);

            ComApi comApi = new ComApi(comPort, baudRate);
            comApi.pDevices[netId] = deviceType;

            int retval = lib.MC_Open_ComPort(comApi.pHandle, comApi.pComPort, comApi.pBaudRate);

            if (retval != 0 & retval != 4) {
                throw new IOException(libErrorCode(retval, -1, deviceType));
            }

            comPorts[comPort] = comApi;

            if (retval == 4) {
                comApi.unlock(netId, password);
            }

            return comApi;
        }
    }

    public synchronized void disconnect(int netId, boolean lock) throws IOException, IllegalArgumentException {
        if (netId < 0 || netId > 8) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.NETID));
        }
        if (lock) {
            lock(netId);
        }
        if (hasSingleDevice()) {
            lib.MC_Close_ComPort(pHandle.getValue());
            comPorts[pComPort] = null;
        } else {
            pDevices[netId] = null;
        }
    }

    private void unlock(int netId, String password) throws IOException, IllegalArgumentException {

        if (netId < 0 || netId > 8) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.NETID));
        }

        if (password == null || password == "") {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.NO_PASSWORD));
        }

        ByteByReference pError = new ByteByReference((byte) 0);

        int retval = lib.MC_Unlock_Device(pHandle.getValue(), (byte) netId, password, pError);

        if (retval != 0) {
            throw new IOException(libErrorCode(retval, pError.getValue(), pDevices[netId]));
        }
    }

    private void lock(int netId) throws IOException, IllegalArgumentException {

        ByteByReference pError = new ByteByReference((byte) 0);

        if (netId < 0 || netId > 8) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.NETID));
        }

        int retval = lib.MC_Lock_Device(pHandle.getValue(), (byte) netId, pError);

        if (retval != 0) {
            throw new IOException(libErrorCode(retval, pError.getValue(), pDevices[netId]));
        }
    }

    private boolean hasSingleDevice() {
        int cnt = 0;
        for (DeviceType t : pDevices) {
            if (t != null) {
                cnt++;
            }
        }
        if (cnt < 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean[] getValuesArray(int netId, ElementType elementType) throws IllegalArgumentException, IOException {

        if (netId < 0 || netId > 8) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.NETID));
        }

        byte length = 0;
        short easyNetId = 0;
        byte bData[] = new byte[80];

        int retval = lib.MC_Read_Object_Value(pHandle.getValue(), (byte) netId, elementType.val(), easyNetId, length,
                bData);

        if (retval != 0) {
            throw new IOException(libErrorCode(retval, bData[0], pDevices[netId]));
        }

        boolean[] retValues = new boolean[641];

        for (int i = 0; i < bData.length; i++) {
            retValues[i * 8 + 1] = (bData[i] & 1) == 1;
            retValues[i * 8 + 2] = (bData[i] & 2) == 2;
            retValues[i * 8 + 3] = (bData[i] & 4) == 4;
            retValues[i * 8 + 4] = (bData[i] & 8) == 8;
            retValues[i * 8 + 5] = (bData[i] & 16) == 16;
            retValues[i * 8 + 6] = (bData[i] & 32) == 32;
            retValues[i * 8 + 7] = (bData[i] & 64) == 64;
            retValues[i * 8 + 8] = (bData[i] & 128) == 128;
        }

        return retValues;
    }

    public boolean getValue(int netId, ElementType elementType, int index)
            throws IllegalArgumentException, IOException {
        if (index < 0 || index > 96) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.MARKER_RANGE));
        }
        return getValuesArray(netId, elementType)[index];
    }

    public void setValue(int netId, ElementType elementType, int index, boolean enabled)
            throws IllegalArgumentException, IOException {

        if (netId < 0 || netId > 8) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.NETID));
        }

        if (index < 1 || index > 96) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.MARKER_RANGE));
        }

        if (elementType != ElementType.BIT_MARKER__M1_M96) {
            throw new IllegalArgumentException(packageErrorCode(ErrorType.ELEMENT_TYPE));
        }

        byte length = 0;
        byte bData[] = new byte[80];

        if (enabled) {
            bData[0] = 1;
        } else {
            bData[0] = 0;
        }

        int retval = lib.MC_Write_Object_Value(pHandle.getValue(), (byte) netId, elementType.val(), (short) index,
                length, bData);

        if (retval != 0) {
            throw new IOException(libErrorCode(retval, bData[0], pDevices[netId]));
        }
    }

    private static String packageErrorCode(ErrorType errorType) {
        switch (errorType) {
            case DEVICE_EXISTS:
                return "Device on this address already connected";
            case DEVICE_TYPE:
                return "Device type not set";
            case PORT:
                return "Invalid COM-port provided. Should be in 0..255 range";
            case TIMEOUT:
                return "Invalid timeout provided. Should be in 0..90000 range";
            case NETID:
                return "netID should be in 0..8 range";
            case MARKER_RANGE:
                return "Marker index should be in 1..96 range";
            case ELEMENT_TYPE:
                return "Illegal Element-type. Supports only Markers";
            case NO_PASSWORD:
                return "No password provided";
            default:
                return "Undefined error";
        }
    }

    private static String libErrorCode(int errorCode, int deviceErrorCode, DeviceType type) {
        switch (errorCode) {
            case 0:
                return "Function call up successful.";
            case 1:
                return "A parameter contains an invalid value.";
            case 2:
                return "Device sends an error message: " + deviceCode(deviceErrorCode, type);
            case 3:
                return "Device does not respond.";
            case 6:
                int sysError = lib.GetLastSystemError();
                return "Error message from Windows. Interrogate error code with GetLastSystemError. Error code: "
                        + sysError + "\nRefer to http://msdn2.microsoft.com/en-us/library/ms681381.aspx";
            case 7:
                return "No connection open.";
            case 8:
                return "The COM port is no longer present or no longer registered in the system (connection break)";
            case 9:
                return "The COM port is blocked by another process or not registered in the system (connection break).";
            case 10:
                return "General communication error occurred (possible hardware failure)";
            case 11:
                return "Internal error of EASY_COM.dll";
            case 16:
                return "The connection handle is not valid (anymore)";
            default:
                return "Undefined error";
        }
    }

    private static String deviceCode(int errorCode, DeviceType type) {
        switch (type) {
            case EASY_500_700:
                switch (errorCode) {
                    case (2):
                        return "Function/operation is not supported";
                    case (3):
                        return "Function/operation is not supported";
                    case (4):
                        return "Entity number is invalid";
                    case (5):
                        return "Parameter value is invalid";
                    case (6):
                        return "Write access on a element parameter that doesn't contain a constant";
                    case (12):
                        return "Function/operation only permissible in STOP";
                    case (13):
                        return "Display indication is not in normal position (input running)";
                    case (16):
                        return "Transmitted password does not agree with password in the device.";
                    case (101):
                        return "Password protection is active. Device must be unlocked.";
                    default:
                        return "Undefined";
                }
            case EASY_800_MFD:
                switch (errorCode) {
                    case (6):
                        return "Function/operation is not supported";
                    case (7):
                        return "Function/operation is not supported";
                    case (8):
                        return "Invalid easyNet ID";
                    case (9):
                        return "easyNet ID larger than 0, but the device is not configured for easyNet";
                    case (10):
                        return "Entity number is invalid";
                    case (18):
                        return "Function/operation only permissible in STOP";
                    case (20):
                        return "Write access on a read only object";
                    case (22):
                        return "Given object size is incorrect";
                    case (26):
                        return "Requested element not available in the program";
                    case (96):
                        return "Password protection is active. Device must be unlocked.";
                    case (97):
                        return "Password protection is active. Device must be unlocked.";
                    case (98):
                        return "Password protection is active. Device must be unlocked.";
                    case (99):
                        return "Password protection is active. Device must be unlocked.";
                    case (100):
                        return "Password protection is active. Device must be unlocked.";
                    case (101):
                        return "Password protection is active. Device must be unlocked.";
                    case (102):
                        return "Password protection is active. Device must be unlocked.";
                    case (103):
                        return "Transmitted password does not agree with password in the device.";
                    case (112):
                        return "Device is not configured for easyNet operation. No communication possible via easyNet.";
                    case (113):
                        return "easyNet out of service";
                    case (114):
                        return "Communication defective. Device is communicating with another station.";
                    case (115):
                        return "easyNet out of service";
                    case (116):
                        return "Communication defective. Device is communicating with another station.";
                    case (126):
                        return "Communication defective. Device is communicating with another station.";
                    case (133):
                        return "Display indication is not in normal position (input running)";
                    case (135):
                        return "The action can not be performed because of a SWD configuration error or the wiring test is still active.";
                    case (176):
                        return "Setting of device clock or setting of summer time configuration rejected because of invalid parameter";
                    case (225):
                        return "Communication defective. Device is communicating with another station.";
                    case (226):
                        return "Communication defective. Device is communicating with another station.";
                    default:
                        return "Undefined";
                }
            default:
                return "Undefined";
        }
    }

}
