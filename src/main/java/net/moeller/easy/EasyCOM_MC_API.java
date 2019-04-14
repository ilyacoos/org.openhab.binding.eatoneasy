package net.moeller.easy;

import com.sun.jna.*;
import com.sun.jna.ptr.*;

public interface EasyCOM_MC_API extends Library {
	//static { Native.register("EASY_COM"); }
	EasyCOM_MC_API INSTANCE = (EasyCOM_MC_API)
			Native.loadLibrary("EASY_COM", EasyCOM_MC_API.class);
	
	public int MC_Open_ComPort
	(
		PointerByReference handle,
		byte            com_port_nr,
		int             baudrate
	);
	
	public int MC_Close_ComPort
		(
			Pointer         handle
		);
	
	public int MC_Open_EthernetPort
		(
			PointerByReference handle,
			String          szIpAddress,
			int             IpPort,
			int             baudrate,
			byte            no_baudrate_scan
		);
	
	public int MC_Close_EthernetPort
		(
			Pointer         handle
		);
	
	public int MC_CloseAll
		(
		);
	
	public int MC_GetCurrent_Baudrate
		(
			Pointer         handle,
			IntByReference  baudrate
		);
		
	public int MC_Set_UserWaitingTime
		(
			int             timeout_delay
		);
	
	public int MC_Get_UserWaitingTime
		(
			IntByReference  timeout_delay
		);
	
	
	public int MC_Start_Program
		(
			Pointer         handle,
			byte            net_id,
			ByteByReference errorcode
		);
	
	public int MC_Stop_Program
		(
			Pointer         handle,
			byte            net_id,
			ByteByReference errorcode
		);
	
	
	public int MC_Read_Clock
		(
			Pointer         handle,
			byte            net_id,
			ByteByReference year,
			ByteByReference month,
			ByteByReference day,
			ByteByReference hour,
			ByteByReference min
		);
	
	public int MC_Write_Clock
		(
			Pointer         handle,
			byte            net_id,
			ByteByReference year,
			ByteByReference month,
			ByteByReference day,
			ByteByReference hour,
			ByteByReference min
		);
	
	
	public int MC_Read_Object_Value
		(
			Pointer         handle,
			byte            net_id,
			byte            object,
			short           index,
			byte            length,
			byte[]          data
		);
	
	public int MC_Write_Object_Value
		(
			Pointer         handle,
			byte            net_id,
			byte            object,
			short           index,
			byte            length,
			byte[]          data
		);
	
	public int MC_Read_Channel_YearTimeSwitch
		(
			Pointer         handle,
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
	
	public int MC_Write_Channel_YearTimeSwitch
		(
			Pointer         handle,
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
	
	public int MC_Read_Channel_7DayTimeSwitch
		(
			Pointer         handle,
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
	
	public int MC_Write_Channel_7DayTimeSwitch
		(
			Pointer         handle,
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
	
	
	public int MC_Unlock_Device
		(
			Pointer         handle,
			byte            net_id,
			String          szPassword,
			ByteByReference errorcode
		);
	
	public int MC_Lock_Device
		(
			Pointer         handle,
			byte            net_id,
			ByteByReference errorcode
		);
	
	public int GetLastSystemError
		(
		);

}
