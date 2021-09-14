package org.openhab.binding.eatoneasy.easyAPI;

import java.io.IOException;

import com.sun.jna.Native;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class Device {
    private static DLLinterfaceMC lib;

    public static enum type {
        EASY_500_700,
        EASY_800_MFD
    }

    public static enum baudRate {
        BR_4800(4800),
        BR_9600(9600),
        BR_19200(19200),
        BR_38400(38400),
        BR_57600(57600);

        private int br;

        baudRate(int br) {
            this.br = br;
        }

        public int val() {
            return br;
        }
    }

    public static enum component {
        DIGITAL_INPUT__I1_I16(0),
        DIGITAL_OUTPUT__Q1_Q8(1),
        DIGITAL_INPUT_EXP__R1_R16(2),
        DIGITAL_OUTPUT_EXP__S1_S8(3),
        BIT_MARKER__M1_M128(4),
        P_BUTTONS(5),
        ANALOG_INPUT__AI1_AI4(8),
        ANALOG_OUTPUT__QA1(9);

        private byte c;

        component(int c) {
            this.c = (byte) c;
        }

        public byte val() {
            return c;
        }
    }

    private PointerByReference pHandle = new PointerByReference();
    private Device.type pType;
    private byte pComPort;
    private int pBaudRate;
    private String pUnlockPassword;

    Device(int comPort) throws UnsatisfiedLinkError, IllegalArgumentException {

        this(comPort, type.EASY_800_MFD, baudRate.BR_19200, "", 800);
    }

    Device(int comPort, Device.type type, Device.baudRate baudRate, String unlockPassword, int timeout)
            throws UnsatisfiedLinkError, IllegalArgumentException {

        if (lib == null) {
            try {
                lib = Native.load("EasyDLL/EASY_COM", DLLinterfaceMC.class);
            } catch (UnsatisfiedLinkError e) {
                throw e;
            }

            setTimeout(timeout);
        }

        if (comPort < 1 || comPort > 255) {
            throw new IllegalArgumentException("COM port should be in 1..255 range");
        } else {
            this.pComPort = (byte) comPort;
        }
        this.pType = type;
        this.pBaudRate = baudRate.val();
        this.pUnlockPassword = unlockPassword;
    }

    static void setTimeout(int timeout) throws IllegalArgumentException {

        if (Device.lib.MC_Set_UserWaitingTime(timeout) > 0) {
            throw new IllegalArgumentException("Invalid timeout provided. Should be in 0..90000 range");
        }
    }

    static int getTimeout() throws IllegalArgumentException {

        IntByReference pWord = new IntByReference(0);

        if (Device.lib.MC_Get_UserWaitingTime(pWord) > 0) {
            throw new IllegalArgumentException("A parameter contains an invalid value.");
        }

        return pWord.getValue();
    }

    void connect() throws IOException {

        int retval = Device.lib.MC_Open_ComPort(pHandle, pComPort, pBaudRate);

        if (retval == 4) {
            unlock(0, pUnlockPassword);
            return;
        }

        if (retval != 0) {
            throw new IOException(libErrorCode(retval, 0));
        }
    }

    void disconnect() {
        Device.lib.MC_Close_ComPort(pHandle.getValue());
    }

    void lock(int netID) throws IOException {

        ByteByReference pError = new ByteByReference((byte) 0);

        if (netID < 0 || netID > 8) {
            throw new IllegalArgumentException("netID should be in 0..8 range");
        }
        int retval = Device.lib.MC_Lock_Device(pHandle.getValue(), (byte) netID, pError);

        if (retval != 0) {
            throw new IOException(libErrorCode(retval, pError.getValue()));
        }
    }

    void unlock(int netID, String password) throws IOException {

        ByteByReference pError = new ByteByReference((byte) 0);

        if (netID < 0 || netID > 8) {
            throw new IllegalArgumentException("netID should be in 0..8 range");
        }

        int retval = Device.lib.MC_Unlock_Device(pHandle.getValue(), (byte) netID, password, pError);

        if (retval != 0) {
            throw new IOException(libErrorCode(retval, pError.getValue()));
        }
    }

    byte[] readValue(int netID, Device.component component, int index) throws IllegalArgumentException, IOException {

        int length = 1;

        if (netID < 0 || netID > 8) {
            throw new IllegalArgumentException("NetID should be in 0..8 range");
        }

        byte bData[] = new byte[80];

        int retval = Device.lib.MC_Read_Object_Value(pHandle.getValue(), (byte) netID, component.val(), (short) index,
                (byte) length, bData);

        if (retval != 0) {
            throw new IOException(libErrorCode(retval, bData[0]));
        }

        return bData;
    }

    void writeValue(int netID, Device.component component, int index, byte bData[])
            throws IllegalArgumentException, IOException {

        int length = 1;

        if (netID < 0 || netID > 8) {
            throw new IllegalArgumentException("NetID should be in 0..8 range");
        }

        if (component.val() != 4 && component.val() != 10 && component.val() != 16 && component.val() != 17) {
            throw new IllegalArgumentException("Can write only to Marker: 4, 10, 16, 17 types");
        }

        int retval = Device.lib.MC_Write_Object_Value(pHandle.getValue(), (byte) netID, component.val(), (short) index,
                (byte) length, bData);

        if (retval != 0) {
            throw new IOException(libErrorCode(retval, bData[0]));
        }
    }

    private String libErrorCode(int errorCode, int deviceErrorCode) {
        switch (errorCode) {
            case 0:
                return "Function call up successful.";
            case 1:
                return "A parameter contains an invalid value.";
            case 2:
                return "Device sends an error message: " + deviceCode(pType, deviceErrorCode);
            case 3:
                return "Device does not respond.";
            case 6:
                int sysError = Device.lib.GetLastSystemError();
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

    private String deviceCode(Device.type pType, int errorCode) {
        switch (pType) {
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
