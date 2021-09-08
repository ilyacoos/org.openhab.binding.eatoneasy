'***************************************************************************
' *
' *  easy_COM-DLL V2.5.0
' *  Interface declaration file for Microsoft Visual Basic 2005/2008 (VB.NET)
' *
' *  Copyright (c) 2011 by Eaton Industries GmbH, Bonn, Germany.
' *  All rights reserved.
' *  Subject to alterations without notice.
' *
' *  All brand and product names are trademarks or registered trademarks of the owner concerned.
' *
' *  See AM_EASY_COM_G.PDF Document for further information.
' *
'****************************************************************************
Public Class easyCOM

	'****************************************************************************
	' public interface functions for single connection mode

	Declare Ansi Function Open_ComPort Lib "EASY_COM.DLL" _
	(ByVal com_port_nr As Byte, _
	 ByVal baudrate As Integer) As Integer

	Declare Ansi Function Close_ComPort Lib "EASY_COM.DLL" () As Integer

	Declare Ansi Function GetCurrent_Baudrate Lib "EASY_COM.DLL" _
	(ByRef baudrate As Integer) As Integer

	Declare Ansi Function Set_UserWaitingTime Lib "EASY_COM.DLL" _
	(ByVal waittime As Integer) As Integer

	Declare Ansi Function Get_UserWaitingTime Lib "EASY_COM.DLL" _
	(ByRef waittime As Integer) As Integer

	Declare Ansi Function Open_EthernetPort Lib "EASY_COM.DLL" _
	(ByVal szIpAddress As String, _
	 ByVal IpPort As Integer, _
	 ByVal baudrate As Integer, _
	 ByVal no_baudrate_scan As Boolean) As Integer

	Declare Ansi Function Close_EthernetPort Lib "EASY_COM.DLL" () As Integer

	Declare Ansi Function Start_Program Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, ByRef Errorcode As Byte) As Integer

	Declare Ansi Function Stop_Program Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, ByRef Errorcode As Byte) As Integer

	Declare Ansi Function Read_Clock Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, _
	 ByRef year As Byte, _
	 ByRef month As Byte, _
	 ByRef day As Byte, _
	 ByRef hour As Byte, _
	 ByRef min As Byte) As Integer

	Declare Ansi Function Write_Clock Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, _
	 ByRef year As Byte, _
	 ByRef month As Byte, _
	 ByRef day As Byte, _
	 ByRef hour As Byte, _
	 ByRef min As Byte) As Integer

	Declare Ansi Function Read_Object_Value Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, _
	 ByVal obj As Byte, _
	 ByVal index As UShort, _
	 ByRef data As Byte) As Integer

	Declare Ansi Function Write_Object_Value Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, _
	 ByVal obj As Byte, _
	 ByVal index As UShort, _
	 ByVal length As Byte, _
	 ByRef data As Byte) As Integer

	Declare Ansi Function Read_Channel_YearTimeSwitch Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, _
	 ByVal index As Byte, _
	 ByVal channel As Byte, _
	 ByRef on_year As Byte, _
	 ByRef on_month As Byte, _
	 ByRef on_day As Byte, _
	 ByRef off_year As Byte, _
	 ByRef off_month As Byte, _
	 ByRef off_day As Byte) As Integer

	Declare Ansi Function Write_Channel_YearTimeSwitch Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, _
	 ByVal index As Byte, _
	 ByVal channel As Byte, _
	 ByRef on_year As Byte, _
	 ByRef on_month As Byte, _
	 ByRef on_day As Byte, _
	 ByRef off_year As Byte, _
	 ByRef off_month As Byte, _
	 ByRef off_day As Byte) As Integer

	Declare Ansi Function Read_Channel_7DayTimeSwitch Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, _
	 ByVal index As Byte, _
	 ByVal channel As Byte, _
	 ByRef DY1 As Byte, _
	 ByRef DY2 As Byte, _
	 ByRef on_hour As Byte, _
	 ByRef on_minute As Byte, _
	 ByRef off_hour As Byte, _
	 ByRef off_minute As Byte) As Integer

	Declare Ansi Function Write_Channel_7DayTimeSwitch Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, _
	 ByVal index As Byte, _
	 ByVal channel As Byte, _
	 ByRef DY1 As Byte, _
	 ByRef DY2 As Byte, _
	 ByRef on_hour As Byte, _
	 ByRef on_minute As Byte, _
	 ByRef off_hour As Byte, _
	 ByRef off_minute As Byte) As Integer

	Declare Ansi Function Unlock_Device Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, _
	 ByVal szPassword As String, _
	 ByRef Errorcode As Byte) As Integer

	Declare Ansi Function Lock_Device Lib "EASY_COM.DLL" _
	(ByVal net_id As Byte, _
	 ByRef Errorcode As Byte) As Integer

	'****************************************************************************
	' public interface functions for multiple connection mode
	'
	Declare Ansi Function MC_Open_ComPort Lib "EASY_COM.DLL" _
	(ByRef handle As Integer, _
	 ByVal com_port_nr As Byte, _
	 ByVal baudrate As Integer) As Integer

	Declare Ansi Function MC_Close_ComPort Lib "EASY_COM.DLL" _
	(ByVal handle As Integer) As Integer

	Declare Ansi Function MC_GetCurrent_Baudrate Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByRef baudrate As Integer) As Integer

	Declare Ansi Function MC_Set_UserWaitingTime Lib "EASY_COM.DLL" _
	(ByVal waittime As Integer) As Integer

	Declare Ansi Function MC_Get_UserWaitingTime Lib "EASY_COM.DLL" _
	(ByRef waittime As Integer) As Integer

	Declare Ansi Function MC_Open_EthernetPort Lib "EASY_COM.DLL" _
	(ByRef handle As Integer, _
	 ByVal szIpAddress As String, _
	 ByVal IpPort As Integer, _
	 ByVal baudrate As Integer, _
	 ByVal no_baudrate_scan As Boolean) As Integer

	Declare Ansi Function MC_Close_EthernetPort Lib "EASY_COM.DLL" _
	(ByVal handle As Integer) As Integer

	Declare Ansi Function MC_CloseAll Lib "EASY_COM.DLL" () As Integer

	Declare Ansi Function MC_Start_Program Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, ByRef Errorcode As Byte) As Integer

	Declare Ansi Function MC_Stop_Program Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, ByRef Errorcode As Byte) As Integer

	Declare Ansi Function MC_Read_Clock Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, _
	 ByRef year As Byte, _
	 ByRef month As Byte, _
	 ByRef day As Byte, _
	 ByRef hour As Byte, _
	 ByRef min As Byte) As Integer

	Declare Ansi Function MC_Write_Clock Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, _
	 ByRef year As Byte, _
	 ByRef month As Byte, _
	 ByRef day As Byte, _
	 ByRef hour As Byte, _
	 ByRef min As Byte) As Integer

	Declare Ansi Function MC_Read_Object_Value Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, _
	 ByVal obj As Byte, _
	 ByVal index As UShort, _
	 ByVal length As Byte, _
	 ByRef data As Byte) As Integer


	Declare Ansi Function MC_Write_Object_Value Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, _
	 ByVal obj As Byte, _
	 ByVal index As UShort, _
	 ByVal length As Byte, _
	 ByRef data As Byte) As Integer

	Declare Ansi Function MC_Read_Channel_YearTimeSwitch Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, _
	 ByVal index As Byte, _
	 ByVal channel As Byte, _
	 ByRef on_year As Byte, _
	 ByRef on_month As Byte, _
	 ByRef on_day As Byte, _
	 ByRef off_year As Byte, _
	 ByRef off_month As Byte, _
	 ByRef off_day As Byte) As Integer

	Declare Ansi Function MC_Write_Channel_YearTimeSwitch Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, _
	 ByVal index As Byte, _
	 ByVal channel As Byte, _
	 ByRef on_year As Byte, _
	 ByRef on_month As Byte, _
	 ByRef on_day As Byte, _
	 ByRef off_year As Byte, _
	 ByRef off_month As Byte, _
	 ByRef off_day As Byte) As Integer

	Declare Ansi Function MC_Read_Channel_7DayTimeSwitch Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, _
	 ByVal index As Byte, _
	 ByVal channel As Byte, _
	 ByRef DY1 As Byte, _
	 ByRef DY2 As Byte, _
	 ByRef on_hour As Byte, _
	 ByRef on_minute As Byte, _
	 ByRef off_hour As Byte, _
	 ByRef off_minute As Byte) As Integer

	Declare Ansi Function MC_Write_Channel_7DayTimeSwitch Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, _
	 ByVal index As Byte, _
	 ByVal channel As Byte, _
	 ByRef DY1 As Byte, _
	 ByRef DY2 As Byte, _
	 ByRef on_hour As Byte, _
	 ByRef on_minute As Byte, _
	 ByRef off_hour As Byte, _
	 ByRef off_minute As Byte) As Integer

	Declare Ansi Function MC_Unlock_Device Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, _
	 ByVal szPassword As String, _
	 ByRef Errorcode As Byte) As Integer

	Declare Ansi Function MC_Lock_Device Lib "EASY_COM.DLL" _
	(ByVal handle As Integer, _
	 ByVal net_id As Byte, _
	 ByRef Errorcode As Byte) As Integer

	'****************************************************************************
	' Windows system access functions

	Declare Ansi Function GetLastSystemError Lib "EASY_COM.DLL" () As Integer

	'****************************************************************************
	' possible function return codes

	Public Const easy_COM_Err_Success As Integer = 0

	Public Const easy_COM_Err_InvalidParameter As Integer = 1

	Public Const easy_COM_Err_DeviceAdverseResponse As Integer = 2
	Public Const easy_COM_Err_DeviceNoResponse As Integer = 3
	Public Const easy_COM_Err_DevicePasswortLock As Integer = 4
	Public Const easy_COM_Err_DeviceTypeUnknown As Integer = 5

	Public Const easy_COM_Err_WindowsSystemError As Integer = 6

	Public Const easy_COM_Err_ComPortNotOpen As Integer = 7
	Public Const easy_COM_Err_ComPortNotExist As Integer = 8
	Public Const easy_COM_Err_ComPortCannotAccessed As Integer = 9
	Public Const easy_COM_Err_ComGeneralError As Integer = 10

	Public Const easy_COM_Err_InternalError As Integer = 11

	Public Const easy_COM_Err_FB_NotExist As Integer = 12
	Public Const easy_COM_Err_FB_NoConstParameter As Integer = 13

	Public Const easy_COM_Err_TcpPortNotExist As Integer = 14
	Public Const easy_COM_Err_TcpPortNoBaudrateScanPossible As Integer = 15

	Public Const easy_COM_Err_HandleInvalid As Integer = 16

End Class

