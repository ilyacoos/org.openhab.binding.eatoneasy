/***************************************************************************
 *
 *  easy_COM-DLL V2.5.0
 *  Interface declaration file for Microsoft Visual C++
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

#ifndef easyComApi_h_included
#define easyComApi_h_included

#ifdef __cplusplus
extern "C" {
#endif

	// public interface functions for single connection mode

	_declspec(dllimport) long __stdcall Open_ComPort
		(
			unsigned char   com_port_nr,
			long            baudrate
		);
	
	_declspec(dllimport) long __stdcall Close_ComPort
		(
		);

	_declspec(dllimport) long __stdcall Open_EthernetPort
		(
			const char      *szIpAddress,
			long            IpPort,
			long            baudrate,
			bool            no_baudrate_scan
		);

	_declspec(dllimport) long __stdcall Close_EthernetPort
		(
		);

	_declspec(dllimport) long __stdcall Set_UserWaitingTime
		(
			long            timeout_delay
		);

	_declspec(dllimport) long __stdcall Get_UserWaitingTime
		(
			long            *timeout_delay
		);

	_declspec(dllimport) long __stdcall GetCurrent_Baudrate
		(
			long            *baudrate
		);


	_declspec(dllimport) long __stdcall Start_Program
		(
			unsigned char   net_id,
			unsigned char   *errorcode
		);

	_declspec(dllimport) long __stdcall Stop_Program
		(
			unsigned char   net_id,
			unsigned char   *errorcode
		);
	

	_declspec(dllimport) long __stdcall Read_Clock
		(
			unsigned char   net_id,
			unsigned char   *year,
			unsigned char   *month,
			unsigned char   *day,
			unsigned char   *hour,
			unsigned char   *min
		);

	_declspec(dllimport) long __stdcall Write_Clock
		(
			unsigned char   net_id,
			unsigned char   *year,
			unsigned char   *month,
			unsigned char   *day,
			unsigned char   *hour,
			unsigned char   *min
		);


	_declspec(dllimport) long __stdcall Read_Object_Value
		(
			unsigned char   net_id,
			unsigned char   object,
			unsigned short  index,
			unsigned char   *data
		);

	_declspec(dllimport) long __stdcall Write_Object_Value
		(
			unsigned char   net_id,
			unsigned char   object,
			unsigned short  index,
			unsigned char   length,
			unsigned char   *data
		);


	_declspec(dllimport) long __stdcall Read_Channel_YearTimeSwitch
		(
			unsigned char   net_id,
			unsigned char   index,
			unsigned char   channel,
			unsigned char   *on_year,
			unsigned char   *on_month,
			unsigned char   *on_day,
			unsigned char   *off_year,
			unsigned char   *off_month,
			unsigned char   *off_day
		);

	_declspec(dllimport) long __stdcall Write_Channel_YearTimeSwitch
		(
			unsigned char   net_id,
			unsigned char   index,
			unsigned char   channel,
			unsigned char   *on_year,
			unsigned char   *on_month,
			unsigned char   *on_day,
			unsigned char   *off_year,
			unsigned char   *off_month,
			unsigned char   *off_day
		);


	_declspec(dllimport) long __stdcall Read_Channel_7DayTimeSwitch
		(
			unsigned char   net_id,
			unsigned char   index,
			unsigned char   channel,
			unsigned char   *DY1,
			unsigned char   *DY2,
			unsigned char   *on_hour,
			unsigned char   *on_minute,
			unsigned char   *off_hour,
			unsigned char   *off_minute
		);

	_declspec(dllimport) long __stdcall Write_Channel_7DayTimeSwitch
		(
			unsigned char   net_id,
			unsigned char   index,
			unsigned char   channel,
			unsigned char   *DY1,
			unsigned char   *DY2,
			unsigned char   *on_hour,
			unsigned char   *on_minute,
			unsigned char   *off_hour,
			unsigned char   *off_minute
		);


	_declspec(dllimport) long __stdcall Unlock_Device
		(
			unsigned char   net_id,
			const char      *szPassword,
			unsigned char   *errorcode
		);

	_declspec(dllimport) long __stdcall Lock_Device
		(
			unsigned char   net_id,
			unsigned char   *errorcode
		);


	// public interface functions for multiple connections mode

	typedef void* tEasyComHandle;

	_declspec(dllimport) long __stdcall MC_Open_ComPort
		(
			tEasyComHandle  *phandle,
			unsigned char   com_port_nr,
			long            baudrate
		);
	
	_declspec(dllimport) long __stdcall MC_Close_ComPort
		(
			tEasyComHandle  handle
		);

	_declspec(dllimport) long __stdcall MC_Open_EthernetPort
		(
			tEasyComHandle  *phandle,
			const char      *szIpAddress,
			long            IpPort,
			long            baudrate,
			bool            no_baudrate_scan
		);

	_declspec(dllimport) long __stdcall MC_Close_EthernetPort
		(
			tEasyComHandle  handle
		);

	_declspec(dllimport) long __stdcall MC_CloseAll
		(
		);


	_declspec(dllimport) long __stdcall MC_GetCurrent_Baudrate
		(
			tEasyComHandle  handle,
			long            *baudrate
		);


	_declspec(dllimport) long __stdcall MC_Set_UserWaitingTime
		(
			long            timeout_delay
		);

	_declspec(dllimport) long __stdcall MC_Get_UserWaitingTime
		(
			long            *timeout_delay
		);


	_declspec(dllimport) long __stdcall MC_Start_Program
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   *errorcode
		);

	_declspec(dllimport) long __stdcall MC_Stop_Program
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   *errorcode
		);
	

	_declspec(dllimport) long __stdcall MC_Read_Clock
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   *year,
			unsigned char   *month,
			unsigned char   *day,
			unsigned char   *hour,
			unsigned char   *min
		);

	_declspec(dllimport) long __stdcall MC_Write_Clock
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   *year,
			unsigned char   *month,
			unsigned char   *day,
			unsigned char   *hour,
			unsigned char   *min
		);


	_declspec(dllimport) long __stdcall MC_Read_Object_Value
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   object,
			unsigned short  index,
			unsigned char   length,
			unsigned char   *data
		);

	_declspec(dllimport) long __stdcall MC_Write_Object_Value
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   object,
			unsigned short  index,
			unsigned char   length,
			unsigned char   *data
		);


	_declspec(dllimport) long __stdcall MC_Read_Channel_YearTimeSwitch
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   index,
			unsigned char   channel,
			unsigned char   *on_year,
			unsigned char   *on_month,
			unsigned char   *on_day,
			unsigned char   *off_year,
			unsigned char   *off_month,
			unsigned char   *off_day
		);

	_declspec(dllimport) long __stdcall MC_Write_Channel_YearTimeSwitch
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   index,
			unsigned char   channel,
			unsigned char   *on_year,
			unsigned char   *on_month,
			unsigned char   *on_day,
			unsigned char   *off_year,
			unsigned char   *off_month,
			unsigned char   *off_day
		);


	_declspec(dllimport) long __stdcall MC_Read_Channel_7DayTimeSwitch
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   index,
			unsigned char   channel,
			unsigned char   *DY1,
			unsigned char   *DY2,
			unsigned char   *on_hour,
			unsigned char   *on_minute,
			unsigned char   *off_hour,
			unsigned char   *off_minute
		);

	_declspec(dllimport) long __stdcall MC_Write_Channel_7DayTimeSwitch
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   index,
			unsigned char   channel,
			unsigned char   *DY1,
			unsigned char   *DY2,
			unsigned char   *on_hour,
			unsigned char   *on_minute,
			unsigned char   *off_hour,
			unsigned char   *off_minute
		);


	_declspec(dllimport) long __stdcall MC_Unlock_Device
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			const char      *szPassword,
			unsigned char   *errorcode
		);

	_declspec(dllimport) long __stdcall MC_Lock_Device
		(
			tEasyComHandle  handle,
			unsigned char   net_id,
			unsigned char   *errorcode
		);

	// system access functions

	_declspec(dllimport) unsigned long __stdcall GetLastSystemError
		(
		);

	// possible function return codes
	enum
	{
		eSuccess = 0,

		eInvalidParameter,

		eDeviceAdverseResponse,
		eDeviceNoResponse,
		eDevicePasswortLock,
		eDeviceTypeUnknown,

		eWindowsSystemError,

		eComPortNotOpen,
		eComPortNotExist,
		eComPortCannotAccessed,
		eComGeneralError,

		eInternalError,

		eFB_NotExist,
		eFB_NoConstParameter,

		eTcpPortNotExist,
		eTcpPortNoBaudrateScanPossible,

		eHandleInvalid
	};


#ifdef __cplusplus
}
#endif

#endif
