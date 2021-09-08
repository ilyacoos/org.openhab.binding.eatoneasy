package org.openhab.binding.eatoneasy.api;

import java.text.DateFormat;

/***************************************************************************
*
*  easy_COM-DLL V2.5.0
*  Interface declaration file for Java
*
*  Copyright (c) 2011  by Eaton Industries GmbH, Bonn, Germany.
*  All rights reserved.
*  Subject to alterations without notice.
*
*  All brand and product names are trademarks or registered trademarks of
*  the owner concerned.
*
*  See AM_EASY_COM_G.PDF Document for further information.
*
****************************************************************************
*/
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * @brief "easyCOM Multiple connection API" using Java Native Access
 */
public class easyCOM_API_MC {
    static {
        // Native.register("EASY_COM");
        System.loadLibrary("EASY_COM");
    }

    public static native int MC_Open_ComPort(PointerByReference handle, byte com_port_nr, int baudrate);

    public static native int MC_Close_ComPort(Pointer handle);

    public static native int MC_Open_EthernetPort(PointerByReference handle, String szIpAddress, int IpPort,
            int baudrate, byte no_baudrate_scan);

    public static native int MC_Close_EthernetPort(Pointer handle);

    public static native int MC_CloseAll();

    public static native int MC_GetCurrent_Baudrate(Pointer handle, IntByReference baudrate);

    public static native int MC_Set_UserWaitingTime(int timeout_delay);

    public static native int MC_Get_UserWaitingTime(IntByReference timeout_delay);

    public static native int MC_Start_Program(Pointer handle, byte net_id, ByteByReference errorcode);

    public static native int MC_Stop_Program(Pointer handle, byte net_id, ByteByReference errorcode);

    public static native int MC_Read_Clock(Pointer handle, byte net_id, ByteByReference year, ByteByReference month,
            ByteByReference day, ByteByReference hour, ByteByReference min);

    public static native int MC_Write_Clock(Pointer handle, byte net_id, ByteByReference year, ByteByReference month,
            ByteByReference day, ByteByReference hour, ByteByReference min);

    public static native int MC_Read_Object_Value(Pointer handle, byte net_id, byte object, short index, byte length,
            byte[] data);

    public static native int MC_Write_Object_Value(Pointer handle, byte net_id, byte object, short index, byte length,
            byte[] data);

    public static native int MC_Read_Channel_YearTimeSwitch(Pointer handle, byte net_id, byte index, byte channel,
            ByteByReference on_year, ByteByReference on_month, ByteByReference on_day, ByteByReference off_year,
            ByteByReference off_month, ByteByReference off_day);

    public static native int MC_Write_Channel_YearTimeSwitch(Pointer handle, byte net_id, byte index, byte channel,
            ByteByReference on_year, ByteByReference on_month, ByteByReference on_day, ByteByReference off_year,
            ByteByReference off_month, ByteByReference off_day);

    public static native int MC_Read_Channel_7DayTimeSwitch(Pointer handle, byte net_id, byte index, byte channel,
            ByteByReference DY1, ByteByReference DY2, ByteByReference on_hour, ByteByReference on_minute,
            ByteByReference off_hour, ByteByReference off_minute);

    public static native int MC_Write_Channel_7DayTimeSwitch(Pointer handle, byte net_id, byte index, byte channel,
            ByteByReference DY1, ByteByReference DY2, ByteByReference on_hour, ByteByReference on_minute,
            ByteByReference off_hour, ByteByReference off_minute);

    public static native int MC_Unlock_Device(Pointer handle, byte net_id, String szPassword,
            ByteByReference errorcode);

    public static native int MC_Lock_Device(Pointer handle, byte net_id, ByteByReference errorcode);

    public static native int GetLastSystemError();

    // usage demo
    public static void main(String[] args) {
        int success;
        PointerByReference pHandle = new PointerByReference();
        IntByReference pWord = new IntByReference(0);

        System.out.println("Hallo easy:");

        success = MC_Set_UserWaitingTime(500);
        System.out.println("MC_Set_UserWaitingTime( 500 ) => " + success);

        success = MC_Get_UserWaitingTime(pWord);
        System.out.println("MC_Get_UserWaitingTime( ) => " + pWord.getValue());

        success = MC_Open_ComPort(pHandle, (byte) 1, 19200);
        System.out.println("MC_OpenComPort( 1, 19200 ) => " + success);

        if (success == 0) {
            byte NT0 = 0;
            byte bData[] = new byte[80];

            ByteByReference pError = new ByteByReference((byte) 0);
            ByteByReference pYY = new ByteByReference((byte) 0);
            ByteByReference pMM = new ByteByReference((byte) 0);
            ByteByReference pDD = new ByteByReference((byte) 0);
            ByteByReference pHH = new ByteByReference((byte) 0);
            ByteByReference pMIN = new ByteByReference((byte) 0);

            success = MC_GetCurrent_Baudrate(pHandle.getValue(), pWord);
            if (success == 0) {
                System.out.println("MC_GetCurrent_Baudrate( ) => " + pWord.getValue());
            }

            success = MC_Read_Clock(pHandle.getValue(), NT0, pYY, pMM, pDD, pHH, pMIN);
            if (success == 0) {
                java.util.GregorianCalendar dt = new java.util.GregorianCalendar();
                java.text.DateFormat dtFormatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);

                dt.set(2000 + pYY.getValue(), pMM.getValue() - 1, pDD.getValue(), pHH.getValue(), pMIN.getValue());
                System.out.println("MC_Read_Clock(...) => " + dtFormatter.format(dt.getTime()));
            } else {
                System.out.println("MC_Read_Clock(...) => error " + success);
            }

            success = MC_Read_Object_Value(pHandle.getValue(), NT0, (byte) 0, (short) 0, (byte) 2, bData);
            if (success == 0) {
                System.out.printf("MC_Read_Object_Value(I) => hex %02X%02X \n", bData[1], bData[0]);
            } else {
                System.out.println("MC_Read_Object_Value(I) => error " + success);
            }

            success = MC_Stop_Program(pHandle.getValue(), NT0, pError);
            System.out.println("MC_Stop_Program(...) => " + success);

            success = MC_Start_Program(pHandle.getValue(), NT0, pError);
            System.out.println("MC_Start_Program(...) => " + success);

            success = MC_Lock_Device(pHandle.getValue(), NT0, pError);
            System.out.println("MC_Lock_Device(...) => " + success);

            success = MC_Unlock_Device(pHandle.getValue(), NT0, "123456", pError);
            System.out.println("MC_Unlock_Device(...) => " + success);

            success = MC_Close_ComPort(pHandle.getValue());
            System.out.println("MC_Close_ComPort( ) => " + success);
        }

        success = MC_Open_EthernetPort(pHandle, "192.168.1.162", 10001, 38400, (byte) 0);
        System.out.println("MC_Open_EthernetPort(...) => " + success);

        success = MC_Close_EthernetPort(pHandle.getValue());
        System.out.println("MC_Close_EthernetPort(...) => " + success);
    }
}
