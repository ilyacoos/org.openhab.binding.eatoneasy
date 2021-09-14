/***************************************************************************
 *
 *  easy_COM-DLL V2.5.0
 *  C# Interface class for MS Visual C++ 2005/2008
 *
 *  Copyright (c) 2011 by Eaton Industries GmbH, Bonn, Germany.
 *  All rights reserved.
 *  Subject to alterations without notice.
 *
 *  All brand and product names are trademarks or registered trademarks of the owner concerned.
 *
 *  See AM_EASY_COM_G.PDF Document for further information.
 *
 ****************************************************************************
 */

using System;
using System.Runtime.InteropServices;

/**
 * @brief single connection API
 */
public class easy_COM_API
{
	// imported interface functions, private for encapsulation

	[DllImport( "EASY_COM", EntryPoint = "Open_ComPort", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Open_ComPort
		(
			byte com_port_nr,
			int baudrate
		);

	[DllImport( "EASY_COM", EntryPoint = "Close_ComPort", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Close_ComPort
		(
		);

	[DllImport( "EASY_COM", EntryPoint = "GetCurrent_Baudrate", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_GetCurrent_Baudrate
		(
			out int baudrate
		);


	[DllImport( "EASY_COM", EntryPoint = "Set_UserWaitingTime", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Set_UserWaitingTime
		(
			int timeout_delay
		);

	[DllImport( "EASY_COM", EntryPoint = "Get_UserWaitingTime", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Get_UserWaitingTime
		(
			out int timeout_delay
		);


	[DllImport( "EASY_COM", EntryPoint = "Start_Program", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Start_Program
		(
			byte net_id,
			out byte errorcode
		);

	[DllImport( "EASY_COM", EntryPoint = "Stop_Program", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Stop_Program
		(
			byte net_id,
			out byte errorcode
		);


	[DllImport( "EASY_COM", EntryPoint = "Read_Clock", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Read_Clock
		(
			byte net_id,
			out byte year,
			out byte month,
			out byte day,
			out byte hour,
			out byte min
		);

	[DllImport( "EASY_COM", EntryPoint = "Write_Clock", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Write_Clock
		(
			byte net_id,
			ref byte year,
			ref byte month,
			ref byte day,
			ref byte hour,
			ref byte min
		);


	[DllImport( "EASY_COM", EntryPoint = "Read_Object_Value", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Read_Object_Value
		(
			byte   net_id,
			byte   obj,
			ushort index,
			[MarshalAs( UnmanagedType.LPArray )] byte[] data
		);

	[DllImport( "EASY_COM", EntryPoint = "Write_Object_Value", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Write_Object_Value
		(
			byte   net_id,
			byte   obj,
			ushort index,
			byte   length,
			[MarshalAs( UnmanagedType.LPArray )] byte[] data
		);


	[DllImport( "EASY_COM", EntryPoint = "Read_Channel_YearTimeSwitch", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Read_Channel_YearTimeSwitch
		(
			byte net_id,
			byte index,
			byte channel,
			out byte on_year,
			out byte on_month,
			out byte on_day,
			out byte off_year,
			out byte off_month,
			out byte off_day
		);

	[DllImport( "EASY_COM", EntryPoint = "Write_Channel_YearTimeSwitch", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Write_Channel_YearTimeSwitch
		(
			byte net_id,
			byte index,
			byte channel,
			ref byte on_year,
			ref byte on_month,
			ref byte on_day,
			ref byte off_year,
			ref byte off_month,
			ref byte off_day
		);


	[DllImport( "EASY_COM", EntryPoint = "Read_Channel_7DayTimeSwitch", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Read_Channel_7DayTimeSwitch
		(
			byte net_id,
			byte index,
			byte channel,
			out byte DY1,
			out byte DY2,
			out byte on_hour,
			out byte on_minute,
			out byte off_hour,
			out byte off_minute
		);

	[DllImport( "EASY_COM", EntryPoint = "Write_Channel_7DayTimeSwitch", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Write_Channel_7DayTimeSwitch
		(
			byte net_id,
			byte index,
			byte channel,
			ref byte DY1,
			ref byte DY2,
			ref byte on_hour,
			ref byte on_minute,
			ref byte off_hour,
			ref byte off_minute
		);


	[DllImport( "EASY_COM", EntryPoint = "Open_EthernetPort", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Open_EthernetPort
		(
			[MarshalAs(UnmanagedType.LPStr)] String szIpAddress,
			int IpPort,
			int baudrate,
			bool no_baudrate_scan
		);

	[DllImport( "EASY_COM", EntryPoint = "Close_EthernetPort", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Close_EthernetPort
		(
		);


	[DllImport( "EASY_COM", EntryPoint = "Unlock_Device", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Unlock_Device
		(
			byte net_id,
			[MarshalAs( UnmanagedType.LPStr )] String szPassword,
			out byte errorcode
		);

	[DllImport( "EASY_COM", EntryPoint = "Lock_Device", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Lock_Device
		(
			byte net_id,
			out byte errorcode
		);


	[DllImport( "EASY_COM", EntryPoint = "GetLastSystemError", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern uint Unmanaged_GetLastSystemError
		(
		);

	// function error codes
	public enum eErrorCodes
	{
		eException = -1,

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
		eTcpPortNoBaudrateScanPossible
	};

	// public interface functions to catch
	// System.DllNotFoundException, System.EntryPointNotFoundException

	public static eErrorCodes Open_ComPort
		(
			int com_port_nr,
			int baudrate
		)
	{
		try
		{
			if ( com_port_nr < 1 || com_port_nr > 255 )
				return eErrorCodes.eInvalidParameter;

			return (eErrorCodes) Unmanaged_Open_ComPort( (byte)com_port_nr, baudrate );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Close_ComPort
		(
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Close_ComPort( );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes GetCurrent_Baudrate
		(
			out int baudrate
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_GetCurrent_Baudrate( out baudrate );
		}
		catch ( Exception )
		{
			baudrate = 0;
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes Set_UserWaitingTime
		(
			int timeout_delay
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Set_UserWaitingTime( timeout_delay );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}
   
	public static eErrorCodes Get_UserWaitingTime
		(
			out int timeout_delay
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Get_UserWaitingTime( out timeout_delay );
		}
		catch ( Exception )
		{
			timeout_delay = 0;
			return eErrorCodes.eException;
		}
	}
   
   
	public static eErrorCodes Start_Program
		(
			byte net_id,
			out byte errorcode
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Start_Program( net_id, out errorcode );
		}
		catch ( Exception )
		{
			errorcode = 0;
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Stop_Program
		(
			byte net_id,
			out byte errorcode
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Stop_Program( net_id, out errorcode );
		}
		catch ( Exception )
		{
			errorcode = 0;
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes Read_Clock
		(
			byte net_id,
			out byte year,
			out byte month,
			out byte day,
			out byte hour,
			out byte min
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Read_Clock( net_id, out year, out month, out day, out hour, out min );
		}
		catch ( Exception )
		{
			year = 0;
			month = 0;
			day = 0;
			hour = 0;
			min = 0;
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Write_Clock
		(
			byte net_id,
			ref byte year,
			ref byte month,
			ref byte day,
			ref byte hour,
			ref byte min
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Write_Clock( net_id, ref year, ref month, ref day, ref hour, ref min );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes Read_Object_Value
		(
			byte net_id,
			byte obj,
			ushort index,
			ref byte[] data
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Read_Object_Value( net_id, obj, index, data );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Write_Object_Value
		(
			byte net_id,
			byte obj,
			ushort index,
			byte length,
			ref byte[] data
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Write_Object_Value( net_id, obj, index, length, data );
		}
		catch ( Exception )
		{
			data[ 0 ] = 0;
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes Read_Channel_YearTimeSwitch
		(
			byte net_id,
			byte index,
			byte channel,
			out byte on_year,
			out byte on_month,
			out byte on_day,
			out byte off_year,
			out byte off_month,
			out byte off_day
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Read_Channel_YearTimeSwitch(
				net_id,
				index,
				channel,
				out on_year,
				out on_month,
				out on_day,
				out off_year,
				out off_month,
				out off_day );
		}
		catch ( Exception )
		{
			on_year = 0;
			on_month = 0;
			on_day = 0;
			off_year = 0;
			off_month = 0;
			off_day = 0;
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Write_Channel_YearTimeSwitch
		(
			byte net_id,
			byte index,
			byte channel,
			ref byte on_year,
			ref byte on_month,
			ref byte on_day,
			ref byte off_year,
			ref byte off_month,
			ref byte off_day
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Write_Channel_YearTimeSwitch(
				net_id,
				index,
				channel,
				ref on_year,
				ref on_month,
				ref on_day,
				ref off_year,
				ref off_month,
				ref off_day );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes Read_Channel_7DayTimeSwitch
		(
			byte net_id,
			byte index,
			byte channel,
			out byte DY1,
			out byte DY2,
			out byte on_hour,
			out byte on_minute,
			out byte off_hour,
			out byte off_minute
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Read_Channel_7DayTimeSwitch(
				net_id,
				index,
				channel,
				out DY1,
				out DY2,
				out on_hour,
				out on_minute,
				out off_hour,
				out off_minute );
		}
		catch ( Exception )
		{
			DY1 = 0;
			DY2 = 0;
			on_hour = 0;
			on_minute = 0;
			off_hour = 0;
			off_minute = 0;
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Write_Channel_7DayTimeSwitch
		(
			byte net_id,
			byte index,
			byte channel,
			ref byte DY1,
			ref byte DY2,
			ref byte on_hour,
			ref byte on_minute,
			ref byte off_hour,
			ref byte off_minute
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Write_Channel_7DayTimeSwitch(
				net_id,
				index,
				channel,
				ref DY1,
				ref DY2,
				ref on_hour,
				ref on_minute,
				ref off_hour,
				ref off_minute );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes Open_EthernetPort
		(
			String sIpAddress,
			int IpPort,
			int baudrate,
			bool no_baudrate_scan
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Open_EthernetPort(
				sIpAddress,
				IpPort,
				baudrate,
				no_baudrate_scan );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Close_EthernetPort
		(
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Close_EthernetPort( );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes Unlock_Device
		(
			byte net_id,
			String sPassword,
			out byte errorcode
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Unlock_Device( net_id, sPassword, out errorcode );
		}
		catch ( Exception )
		{
			errorcode = 0;
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Lock_Device
		(
			byte net_id,
			out byte errorcode
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Lock_Device( net_id, out errorcode );
		}
		catch ( Exception )
		{
			errorcode = 0;
			return eErrorCodes.eException;
		}
	}


	public static uint GetLastSystemError
		(
		)
	{
		try
		{
			return Unmanaged_GetLastSystemError( );
		}
		catch ( Exception )
		{
			return 0xFFFFFFFF;
		}
	}

}
