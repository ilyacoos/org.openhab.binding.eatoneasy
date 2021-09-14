package org.openhab.binding.eatoneasy.easyAPI;

import com.sun.jna.Library;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;

interface DLLinterfaceSC extends Library {
    int Open_ComPort(byte com_port_nr, int baudrate);

    int Close_ComPort();

    int Open_EthernetPort(String szIpAddress, int IpPort, int baudrate, byte no_baudrate_scan);

    int Close_EthernetPort();

    int Set_UserWaitingTime(int timeout_delay);

    int Get_UserWaitingTime(IntByReference timeout_delay);

    int GetCurrent_Baudrate(IntByReference baudrate);

    int Start_Program(byte net_id, ByteByReference errorcode);

    int Stop_Program(byte net_id, ByteByReference errorcode);

    int Read_Clock(byte net_id, ByteByReference year, ByteByReference month, ByteByReference day, ByteByReference hour,
            ByteByReference min);

    int Write_Clock(byte net_id, ByteByReference year, ByteByReference month, ByteByReference day, ByteByReference hour,
            ByteByReference min);

    int Read_Object_Value(byte net_id, byte object, short index, byte[] data);

    int Write_Object_Value(byte net_id, byte object, byte index, short length, byte[] data);

    int Read_Channel_YearTimeSwitch(byte net_id, byte index, byte channel, ByteByReference on_year,
            ByteByReference on_month, ByteByReference on_day, ByteByReference off_year, ByteByReference off_month,
            ByteByReference off_day);

    int Write_Channel_YearTimeSwitch(byte net_id, byte index, byte channel, ByteByReference on_year,
            ByteByReference on_month, ByteByReference on_day, ByteByReference off_year, ByteByReference off_month,
            ByteByReference off_day);

    int Read_Channel_7DayTimeSwitch(byte net_id, byte index, byte channel, ByteByReference DY1, ByteByReference DY2,
            ByteByReference on_hour, ByteByReference on_minute, ByteByReference off_hour, ByteByReference off_minute);

    int Write_Channel_7DayTimeSwitch(byte net_id, byte index, byte channel, ByteByReference DY1, ByteByReference DY2,
            ByteByReference on_hour, ByteByReference on_minute, ByteByReference off_hour, ByteByReference off_minute);

    int Unlock_Device(byte net_id, String szPassword, ByteByReference errorcode);

    int Lock_Device(byte net_id, ByteByReference errorcode);

    int GetLastSystemError();
}
