Attribute VB_Name = "Module1"
'***************************************************************************
' *
' *  easy_COM-DLL V2.5.0
' *  Interface declaration file for Microsoft Visual Basic 6
' *
' *  Copyright (c) 2011  by Eaton Industries GmbH, Bonn, Germany.
' *  All rights reserved.
' *  Subject to alterations without notice.
' *
' *  All brand and product names are trademarks or registered trademarks of the owner concerned.
' *
' *  See AM_EASY_COM_G.PDF Document for further information.
' *
'****************************************************************************
Option Explicit
'
'****************************************************************************
' public interface functions for single connection mode
'
Declare Function Open_ComPort Lib "EASY_COM.DLL" _
    (ByVal com_port_nr As Byte, _
     ByVal baudrate As Long) As Long
'
Declare Function Close_ComPort Lib "EASY_COM.DLL" () As Long
'
Declare Function GetCurrent_Baudrate Lib "EASY_COM.DLL" _
    (ByRef baudrate As Long) As Long
'
'
Declare Function Set_UserWaitingTime Lib "EASY_COM.DLL" _
    (ByVal waittime As Long) As Long
'
Declare Function Get_UserWaitingTime Lib "EASY_COM.DLL" _
    (ByRef waittime As Long) As Long
'
'
Declare Function Open_EthernetPort Lib "EASY_COM.DLL" _
    (ByVal szIpAddress As String, _
     ByVal IpPort As Long, _
     ByVal baudrate As Long, _
     ByVal no_baudrate_scan As Boolean) As Long
'
Declare Function Close_EthernetPort Lib "EASY_COM.DLL" () As Long
'
'
Declare Function Start_Program Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, ByRef Errorcode As Byte) As Long
'
Declare Function Stop_Program Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, ByRef Errorcode As Byte) As Long
'
'
Declare Function Read_Clock Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, _
     ByRef year As Byte, _
     ByRef month As Byte, _
     ByRef day As Byte, _
     ByRef hour As Byte, _
     ByRef min As Byte) As Long
'
Declare Function Write_Clock Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, _
     ByRef year As Byte, _
     ByRef month As Byte, _
     ByRef day As Byte, _
     ByRef hour As Byte, _
     ByRef min As Byte) As Long
'
'
Declare Function Read_Object_Value Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, _
     ByVal object As Byte, _
     ByVal index As Integer, _
     ByRef data As Byte) As Long
'
Declare Function Write_Object_Value Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, _
     ByVal object As Byte, _
     ByVal index As Integer, _
     ByVal length As Byte, _
     ByRef data As Byte) As Long
'
'
Declare Function Read_Channel_YearTimeSwitch Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, _
     ByVal index As Byte, _
     ByVal channel As Byte, _
     ByRef on_year As Byte, _
     ByRef on_month As Byte, _
     ByRef on_day As Byte, _
     ByRef off_year As Byte, _
     ByRef off_month As Byte, _
     ByRef off_day As Byte) As Long
'
Declare Function Write_Channel_YearTimeSwitch Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, _
     ByVal index As Byte, _
     ByVal channel As Byte, _
     ByRef on_year As Byte, _
     ByRef on_month As Byte, _
     ByRef on_day As Byte, _
     ByRef off_year As Byte, _
     ByRef off_month As Byte, _
     ByRef off_day As Byte) As Long
'
'
Declare Function Read_Channel_7DayTimeSwitch Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, _
     ByVal index As Byte, _
     ByVal channel As Byte, _
     ByRef DY1 As Byte, _
     ByRef DY2 As Byte, _
     ByRef on_hour As Byte, _
     ByRef on_minute As Byte, _
     ByRef off_hour As Byte, _
     ByRef off_minute As Byte) As Long
'
Declare Function Write_Channel_7DayTimeSwitch Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, _
     ByVal index As Byte, _
     ByVal channel As Byte, _
     ByRef DY1 As Byte, _
     ByRef DY2 As Byte, _
     ByRef on_hour As Byte, _
     ByRef on_minute As Byte, _
     ByRef off_hour As Byte, _
     ByRef off_minute As Byte) As Long
'
'
Declare Function Unlock_Device Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, _
     ByVal szPassword As String, _
     ByRef Errorcode As Byte) As Long

Declare Function Lock_Device Lib "EASY_COM.DLL" _
    (ByVal net_id As Byte, _
     ByRef Errorcode As Byte) As Long
'
'****************************************************************************
' public interface functions for multiple connection mode
'
Declare Function MC_Open_ComPort Lib "EASY_COM.DLL" _
    (ByRef handle As Long, _
     ByVal com_port_nr As Byte, _
     ByVal baudrate As Long) As Long
'
Declare Function MC_Close_ComPort Lib "EASY_COM.DLL" _
    (ByVal handle As Long) As Long
'
Declare Function MC_GetCurrent_Baudrate Lib "EASY_COM.DLL" _
    (ByVal handle As Long, ByRef baudrate As Long) As Long
'
'
Declare Function MC_Set_UserWaitingTime Lib "EASY_COM.DLL" _
    (ByVal waittime As Long) As Long
'
Declare Function MC_Get_UserWaitingTime Lib "EASY_COM.DLL" _
    (ByRef waittime As Long) As Long
'
'
Declare Function MC_Open_EthernetPort Lib "EASY_COM.DLL" _
    (ByRef handle As Long, _
     ByVal szIpAddress As String, _
     ByVal IpPort As Long, _
     ByVal baudrate As Long, _
     ByVal no_baudrate_scan As Boolean) As Long
'
Declare Function MC_Close_EthernetPort Lib "EASY_COM.DLL" _
    (ByVal handle As Long) As Long
'
'
Declare Function MC_CloseAll Lib "EASY_COM.DLL" () As Long
'
'
Declare Function MC_Start_Program Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, ByRef Errorcode As Byte) As Long
'
Declare Function MC_Stop_Program Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, ByRef Errorcode As Byte) As Long
'
'
Declare Function MC_Read_Clock Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, _
     ByRef year As Byte, _
     ByRef month As Byte, _
     ByRef day As Byte, _
     ByRef hour As Byte, _
     ByRef min As Byte) As Long
'
Declare Function MC_Write_Clock Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, _
     ByRef year As Byte, _
     ByRef month As Byte, _
     ByRef day As Byte, _
     ByRef hour As Byte, _
     ByRef min As Byte) As Long
'
'
Declare Function MC_Read_Object_Value Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, _
     ByVal object As Byte, _
     ByVal index As Integer, _
     ByVal length As Byte, _
     ByRef data As Byte) As Long
'
Declare Function MC_Write_Object_Value Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, _
     ByVal object As Byte, _
     ByVal index As Integer, _
     ByVal length As Byte, _
     ByRef data As Byte) As Long
'
'
Declare Function MC_Read_Channel_YearTimeSwitch Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, _
     ByVal index As Byte, _
     ByVal channel As Byte, _
     ByRef on_year As Byte, _
     ByRef on_month As Byte, _
     ByRef on_day As Byte, _
     ByRef off_year As Byte, _
     ByRef off_month As Byte, _
     ByRef off_day As Byte) As Long
'
Declare Function MC_Write_Channel_YearTimeSwitch Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, _
     ByVal index As Byte, _
     ByVal channel As Byte, _
     ByRef on_year As Byte, _
     ByRef on_month As Byte, _
     ByRef on_day As Byte, _
     ByRef off_year As Byte, _
     ByRef off_month As Byte, _
     ByRef off_day As Byte) As Long
'
'
Declare Function MC_Read_Channel_7DayTimeSwitch Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, _
     ByVal index As Byte, _
     ByVal channel As Byte, _
     ByRef DY1 As Byte, _
     ByRef DY2 As Byte, _
     ByRef on_hour As Byte, _
     ByRef on_minute As Byte, _
     ByRef off_hour As Byte, _
     ByRef off_minute As Byte) As Long
'
Declare Function MC_Write_Channel_7DayTimeSwitch Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, _
     ByVal index As Byte, _
     ByVal channel As Byte, _
     ByRef DY1 As Byte, _
     ByRef DY2 As Byte, _
     ByRef on_hour As Byte, _
     ByRef on_minute As Byte, _
     ByRef off_hour As Byte, _
     ByRef off_minute As Byte) As Long
'
'
Declare Function MC_Unlock_Device Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, _
     ByVal szPassword As String, _
     ByRef Errorcode As Byte) As Long

Declare Function MC_Lock_Device Lib "EASY_COM.DLL" _
    (ByVal handle As Long, _
     ByVal net_id As Byte, _
     ByRef Errorcode As Byte) As Long
'
'****************************************************************************
'  windows system access functions
'
Declare Function GetLastSystemError Lib "EASY_COM.DLL" () As Long
'
'****************************************************************************
' possible function return codes
'
Global Const easy_COM_Err_Success = 0
'
Global Const easy_COM_Err_InvalidParameter = 1
'
Global Const easy_COM_Err_DeviceAdverseResponse = 2
Global Const easy_COM_Err_DeviceNoResponse = 3
Global Const easy_COM_Err_DevicePasswortLock = 4
Global Const easy_COM_Err_DeviceTypeUnknown = 5
'
Global Const easy_COM_Err_WindowsSystemError = 6
'
Global Const easy_COM_Err_ComPortNotOpen = 7
Global Const easy_COM_Err_ComPortNotExist = 8
Global Const easy_COM_Err_ComPortCannotAccessed = 9
Global Const easy_COM_Err_ComGeneralError = 10
'
Global Const easy_COM_Err_InternalError = 11
'
Global Const easy_COM_Err_FB_NotExist = 12
Global Const easy_COM_Err_FB_NoConstParameter = 13
'
Global Const easy_COM_Err_TcpPortNotExist = 14
Global Const easy_COM_Err_TcpPortNoBaudrateScanPossible = 15
'
Global Const easy_COM_Err_HandleInvalid = 16
