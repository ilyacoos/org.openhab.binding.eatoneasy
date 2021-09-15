package org.openhab.binding.eatoneasy.internal.comapi;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

interface DLLinterfaceMC extends Library {
    int MC_Open_ComPort(PointerByReference handle, byte com_port_nr, int baudrate);

    int MC_Close_ComPort(Pointer handle);

    int MC_Open_EthernetPort(PointerByReference handle, String szIpAddress, int IpPort, int baudrate,
            byte no_baudrate_scan);

    int MC_Close_EthernetPort(Pointer handle);

    int MC_CloseAll();

    int MC_GetCurrent_Baudrate(Pointer handle, IntByReference baudrate);

    int MC_Set_UserWaitingTime(int timeout_delay);

    int MC_Get_UserWaitingTime(IntByReference timeout_delay);

    int MC_Start_Program(Pointer handle, byte net_id, ByteByReference errorcode);

    int MC_Stop_Program(Pointer handle, byte net_id, ByteByReference errorcode);

    int MC_Read_Clock(Pointer handle, byte net_id, ByteByReference year, ByteByReference month, ByteByReference day,
            ByteByReference hour, ByteByReference min);

    int MC_Write_Clock(Pointer handle, byte net_id, ByteByReference year, ByteByReference month, ByteByReference day,
            ByteByReference hour, ByteByReference min);

    int MC_Read_Object_Value(Pointer handle, byte net_id, byte object, short index, byte length, byte[] data);

    int MC_Write_Object_Value(Pointer handle, byte net_id, byte object, short index, byte length, byte[] data);

    int MC_Read_Channel_YearTimeSwitch(Pointer handle, byte net_id, byte index, byte channel, ByteByReference on_year,
            ByteByReference on_month, ByteByReference on_day, ByteByReference off_year, ByteByReference off_month,
            ByteByReference off_day);

    int MC_Write_Channel_YearTimeSwitch(Pointer handle, byte net_id, byte index, byte channel, ByteByReference on_year,
            ByteByReference on_month, ByteByReference on_day, ByteByReference off_year, ByteByReference off_month,
            ByteByReference off_day);

    int MC_Read_Channel_7DayTimeSwitch(Pointer handle, byte net_id, byte index, byte channel, ByteByReference DY1,
            ByteByReference DY2, ByteByReference on_hour, ByteByReference on_minute, ByteByReference off_hour,
            ByteByReference off_minute);

    int MC_Write_Channel_7DayTimeSwitch(Pointer handle, byte net_id, byte index, byte channel, ByteByReference DY1,
            ByteByReference DY2, ByteByReference on_hour, ByteByReference on_minute, ByteByReference off_hour,
            ByteByReference off_minute);

    int MC_Unlock_Device(Pointer handle, byte net_id, String szPassword, ByteByReference errorcode);

    int MC_Lock_Device(Pointer handle, byte net_id, ByteByReference errorcode);

    int GetLastSystemError();
}
