package org.openhab.binding.eatoneasy.easyAPI;

import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class Device {
    private static DLLinterfaceMC lib = Native.load("EasyDLL/EASY_COM", DLLinterfaceMC.class);

    PointerByReference pHandle = new PointerByReference();
    byte comPort;
    int baudRate;

    Device(int comPort, int baudRate) {
        this.comPort = (byte) comPort;
        this.baudRate = baudRate;
    }

    static void setTimeout(int timeout) throws Exception {
        if (Device.lib.MC_Set_UserWaitingTime(timeout) > 0) {
            throw new Exception("failed");
        }
    }

    static int getTimeout() throws Exception {
        IntByReference pWord = new IntByReference(0);
        if (Device.lib.MC_Get_UserWaitingTime(pWord) > 0) {
            throw new Exception("failed");
        }

        return pWord.getValue();
    }

    void connect() throws Exception {
        int retval = Device.lib.MC_Open_ComPort(pHandle, comPort, baudRate);
        if (retval > 0) {
            System.out.println(retval);
            throw new Exception("failed connect");
        }
    }

    void disconnect() throws Exception {
        if (Device.lib.MC_Close_ComPort(pHandle.getValue()) > 0) {
            throw new Exception("failed");
        }
    }

    byte[] readValue(int NetID, int object, int index, int length) throws Exception {
        byte bData[] = new byte[80];
        int retval = Device.lib.MC_Read_Object_Value(pHandle.getValue(), (byte) NetID, (byte) object, (short) index, (byte) length, bData);

        if (retval > 0) {
            switch(retval) {
                case (1):  throw new Exception ("A parameter contains an invalid value.");
                case (2):  throw new Exception ("Device sends an error message.");
                case (3):  throw new Exception ("Device does not respond.");
                case (6):  throw new Exception ("Error message from Windows. Interrogate error code with GetLastSystemError.");
                case (7):  throw new Exception ("No connection open.");
                case (8):  throw new Exception ("The COM port is no longer present or no longer registered in the system (connection break)");
                case (9):  throw new Exception ("The COM port is blocked by another process or is no longer registered in the system (connection break).");
                case (10): throw new Exception ("General communication error occurred (possible hardware failure)");
                case (11): throw new Exception ("Internal error of EASY_COM.dll");
                case (16): throw new Exception ("The connection handle is not valid (anymore)");
                default:   throw new Exception ("Undefined error");
            }
        }
        return bData;
    }
}
