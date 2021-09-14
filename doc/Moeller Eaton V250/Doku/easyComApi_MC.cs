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
 * @brief multiple connection API
 */
public class easy_COM_API_MC
{
	// imported interface functions, private for encapsulation

	[DllImport( "EASY_COM", EntryPoint = "MC_Open_ComPort", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Open_ComPort
		(
			out IntPtr handle,
			byte com_port_nr,
			int baudrate
		);

	[DllImport( "EASY_COM", EntryPoint = "MC_Close_ComPort", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Close_ComPort
		(
			IntPtr handle
		);

	[DllImport( "EASY_COM", EntryPoint = "MC_Open_EthernetPort", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Open_EthernetPort
		(
			out IntPtr handle,
			[MarshalAs( UnmanagedType.LPStr )] String szIpAddress,
			int IpPort,
			int baudrate,
			bool no_baudrate_scan
		);

	[DllImport( "EASY_COM", EntryPoint = "MC_Close_EthernetPort", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Close_EthernetPort
		(
			IntPtr handle
		);

	[DllImport( "EASY_COM", EntryPoint = "MC_CloseAll", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_CloseAll
		(
		);


	[DllImport( "EASY_COM", EntryPoint = "MC_GetCurrent_Baudrate", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_GetCurrent_Baudrate
		(
			IntPtr handle,
			out int baudrate
		);

	[DllImport( "EASY_COM", EntryPoint = "MC_Set_UserWaitingTime", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Set_UserWaitingTime
		(
			int timeout_delay
		);

	[DllImport( "EASY_COM", EntryPoint = "MC_Get_UserWaitingTime", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Get_UserWaitingTime
		(
			out int timeout_delay
		);


	[DllImport( "EASY_COM", EntryPoint = "MC_Start_Program", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Start_Program
		(
			IntPtr handle,
			byte net_id,
			out byte errorcode
		);

	[DllImport( "EASY_COM", EntryPoint = "MC_Stop_Program", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Stop_Program
		(
			IntPtr handle,
			byte net_id,
			out byte errorcode
		);


	[DllImport( "EASY_COM", EntryPoint = "MC_Read_Clock", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Read_Clock
		(
			IntPtr handle,
			byte net_id,
			out byte year,
			out byte month,
			out byte day,
			out byte hour,
			out byte min
		);

	[DllImport( "EASY_COM", EntryPoint = "MC_Write_Clock", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Write_Clock
		(
			IntPtr handle,
			byte net_id,
			ref byte year,
			ref byte month,
			ref byte day,
			ref byte hour,
			ref byte min
		);


	[DllImport( "EASY_COM", EntryPoint = "MC_Read_Object_Value", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Read_Object_Value
		(
			IntPtr handle,
			byte net_id,
			byte obj,
			ushort index,
			byte length,
			[MarshalAs( UnmanagedType.LPArray )] byte[] data
		);

	[DllImport( "EASY_COM", EntryPoint = "MC_Write_Object_Value", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Write_Object_Value
		(
			IntPtr handle,
			byte net_id,
			byte obj,
			ushort index,
			byte length,
			[MarshalAs( UnmanagedType.LPArray )] byte[] data
		);


	[DllImport( "EASY_COM", EntryPoint = "MC_Read_Channel_YearTimeSwitch", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Read_Channel_YearTimeSwitch
		(
			IntPtr handle,
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

	[DllImport( "EASY_COM", EntryPoint = "MC_Write_Channel_YearTimeSwitch", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Write_Channel_YearTimeSwitch
		(
			IntPtr handle,
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


	[DllImport( "EASY_COM", EntryPoint = "MC_Read_Channel_7DayTimeSwitch", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Read_Channel_7DayTimeSwitch
		(
			IntPtr handle,
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

	[DllImport( "EASY_COM", EntryPoint = "MC_Write_Channel_7DayTimeSwitch", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Write_Channel_7DayTimeSwitch
		(
			IntPtr handle,
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


	[DllImport( "EASY_COM", EntryPoint = "MC_Unlock_Device", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Unlock_Device
		(
			IntPtr handle,
			byte net_id,
			[MarshalAs( UnmanagedType.LPStr )] String szPassword,
			out byte errorcode
		);

	[DllImport( "EASY_COM", EntryPoint = "MC_Lock_Device", CharSet = CharSet.Ansi, CallingConvention = CallingConvention.StdCall )]
	private static extern int Unmanaged_Lock_Device
		(
			IntPtr handle,
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
		eTcpPortNoBaudrateScanPossible,

		eHandleInvalid
	};

	// public interface functions to catch
	// System.DllNotFoundException, System.EntryPointNotFoundException

	public static eErrorCodes Open_ComPort
		(
			out IntPtr handle,
			int com_port_nr,
			int baudrate
		)
	{
		handle = IntPtr.Zero;
		try
		{
			if ( com_port_nr < 1 || com_port_nr > 255 )
				return eErrorCodes.eInvalidParameter;

			return (eErrorCodes) Unmanaged_Open_ComPort(
				out handle,
				(byte)com_port_nr,
				baudrate );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Close_ComPort
		(
			IntPtr handle
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Close_ComPort( handle );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Open_EthernetPort
		(
			out IntPtr handle,
			String sIpAddress,
			int IpPort,
			int baudrate,
			bool no_baudrate_scan
		)
	{
		handle = IntPtr.Zero;
		try
		{
			return (eErrorCodes) Unmanaged_Open_EthernetPort(
				out handle,
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
			IntPtr handle
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Close_EthernetPort( handle );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes CloseAll
		(
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_CloseAll( );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes GetCurrent_Baudrate
		(
			IntPtr handle,
			out int baudrate
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_GetCurrent_Baudrate( handle, out baudrate );
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
			IntPtr handle,
			byte net_id,
			out byte errorcode
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Start_Program( handle, net_id, out errorcode );
		}
		catch ( Exception )
		{
			errorcode = 0;
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Stop_Program
		(
			IntPtr handle,
			byte net_id,
			out byte errorcode
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Stop_Program( handle, net_id, out errorcode );
		}
		catch ( Exception )
		{
			errorcode = 0;
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes Read_Clock
		(
			IntPtr handle,
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
			return (eErrorCodes) Unmanaged_Read_Clock( handle, net_id, out year, out month, out day, out hour, out min );
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
			IntPtr handle,
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
			return (eErrorCodes) Unmanaged_Write_Clock( handle, net_id, ref year, ref month, ref day, ref hour, ref min );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes Read_Object_Value
		(
			IntPtr handle,
			byte net_id,
			byte obj,
			ushort index,
			byte length,
			ref byte[] data
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Read_Object_Value( handle, net_id, obj, index, length, data );
		}
		catch ( Exception )
		{
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Write_Object_Value
		(
			IntPtr handle,
			byte net_id,
			byte obj,
			ushort index,
			byte length,
			ref byte[] data
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Write_Object_Value( handle, net_id, obj, index, length, data );
		}
		catch ( Exception )
		{
			data[ 0 ] = 0;
			return eErrorCodes.eException;
		}
	}


	public static eErrorCodes Read_Channel_YearTimeSwitch
		(
			IntPtr handle,
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
				handle,
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
			IntPtr handle,
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
				handle,
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
			IntPtr handle,
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
				handle,
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
			IntPtr handle,
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
				handle,
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


	public static eErrorCodes Unlock_Device
		(
			IntPtr handle,
			byte net_id,
			String sPassword,
			out byte errorcode
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Unlock_Device( handle, net_id, sPassword, out errorcode );
		}
		catch ( Exception )
		{
			errorcode = 0;
			return eErrorCodes.eException;
		}
	}

	public static eErrorCodes Lock_Device
		(
			IntPtr handle,
			byte net_id,
			out byte errorcode
		)
	{
		try
		{
			return (eErrorCodes) Unmanaged_Lock_Device( handle, net_id, out errorcode );
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
