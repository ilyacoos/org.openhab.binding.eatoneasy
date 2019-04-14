package net.moeller.easy;

import com.sun.jna.*;
import com.sun.jna.ptr.*;

public interface EasyCOM_API extends Library {
	//static { Native.register("EASY_COM"); }
	EasyCOM_API INSTANCE = (EasyCOM_API)
			Native.loadLibrary("EASY_COM", EasyCOM_API.class);
	
	public int Open_ComPort
		(
			byte com_port_nr,
			int baudrate
		);
		
	public int Close_ComPort
		(
		);

	public int Open_EthernetPort
		(
			String         szIpAddress,
			int            IpPort,
			int            baudrate,
			byte           no_baudrate_scan
		);

	public int Close_EthernetPort
		(
		);

	public int Set_UserWaitingTime
		(
			int            timeout_delay
		);

	public int Get_UserWaitingTime
		(
			IntByReference timeout_delay
		);

	public int GetCurrent_Baudrate
		(
			IntByReference baudrate
		);


	public int Start_Program
		(
			byte            net_id,
			ByteByReference errorcode
		);

	public int Stop_Program
		(
			byte            net_id,
			ByteByReference errorcode
		);
	

	public int Read_Clock
		(
			byte            net_id,
			ByteByReference year,
			ByteByReference month,
			ByteByReference day,
			ByteByReference hour,
			ByteByReference min
		);

	public int Write_Clock
		(
			byte            net_id,
			ByteByReference year,
			ByteByReference month,
			ByteByReference day,
			ByteByReference hour,
			ByteByReference min
		);


	public int Read_Object_Value
		(
			byte            net_id,
			byte            object,
			short           index,
			byte[]          data
		);

	public int Write_Object_Value
		(
			byte            net_id,
			byte            object,
			short           index,
			byte            length,
			byte[]          data
		);

	public int Read_Channel_YearTimeSwitch
		(
			byte            net_id,
			byte            index,
			byte            channel,
			ByteByReference on_year,
			ByteByReference on_month,
			ByteByReference on_day,
			ByteByReference off_year,
			ByteByReference off_month,
			ByteByReference off_day
		);

	public int Write_Channel_YearTimeSwitch
		(
			byte            net_id,
			byte            index,
			byte            channel,
			ByteByReference on_year,
			ByteByReference on_month,
			ByteByReference on_day,
			ByteByReference off_year,
			ByteByReference off_month,
			ByteByReference off_day
		);

	public int Read_Channel_7DayTimeSwitch
		(
			byte            net_id,
			byte            index,
			byte            channel,
			ByteByReference DY1,
			ByteByReference DY2,
			ByteByReference on_hour,
			ByteByReference on_minute,
			ByteByReference off_hour,
			ByteByReference off_minute
		);

	public int Write_Channel_7DayTimeSwitch
		(
			byte            net_id,
			byte            index,
			byte            channel,
			ByteByReference DY1,
			ByteByReference DY2,
			ByteByReference on_hour,
			ByteByReference on_minute,
			ByteByReference off_hour,
			ByteByReference off_minute
		);


	public int Unlock_Device
		(
			byte            net_id,
			String          szPassword,
			ByteByReference errorcode
		);

	public int Lock_Device
		(
			byte            net_id,
			ByteByReference errorcode
		);

	public int GetLastSystemError
		(
		);

}
