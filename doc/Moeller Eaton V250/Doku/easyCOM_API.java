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
 
import com.sun.jna.*;
import com.sun.jna.ptr.*;
 
/**
 * @brief "easyCOM Single connection API" using Java Native Access
 */
public class easyCOM_API
{
	static { Native.register("EASY_COM"); }

	public static native int Open_ComPort
		(
			byte com_port_nr,
			int baudrate
		);
		
	public static native int Close_ComPort
		(
		);

	public static native int Open_EthernetPort
		(
			String         szIpAddress,
			int            IpPort,
			int            baudrate,
			byte           no_baudrate_scan
		);

	public static native int Close_EthernetPort
		(
		);

	public static native int Set_UserWaitingTime
		(
			int            timeout_delay
		);

	public static native int Get_UserWaitingTime
		(
			IntByReference timeout_delay
		);

	public static native int GetCurrent_Baudrate
		(
			IntByReference baudrate
		);


	public static native int Start_Program
		(
			byte            net_id,
			ByteByReference errorcode
		);

	public static native int Stop_Program
		(
			byte            net_id,
			ByteByReference errorcode
		);
	

	public static native int Read_Clock
		(
			byte            net_id,
			ByteByReference year,
			ByteByReference month,
			ByteByReference day,
			ByteByReference hour,
			ByteByReference min
		);

	public static native int Write_Clock
		(
			byte            net_id,
			ByteByReference year,
			ByteByReference month,
			ByteByReference day,
			ByteByReference hour,
			ByteByReference min
		);


	public static native int Read_Object_Value
		(
			byte            net_id,
			byte            object,
			short           index,
			byte[]          data
		);

	public static native int Write_Object_Value
		(
			byte            net_id,
			byte            object,
			byte            index,
			short           length,
			byte[]          data
		);

	public static native int Read_Channel_YearTimeSwitch
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

	public static native int Write_Channel_YearTimeSwitch
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

	public static native int Read_Channel_7DayTimeSwitch
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

	public static native int Write_Channel_7DayTimeSwitch
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


	public static native int Unlock_Device
		(
			byte            net_id,
			String          szPassword,
			ByteByReference errorcode
		);

	public static native int Lock_Device
		(
			byte            net_id,
			ByteByReference errorcode
		);

	public static native int GetLastSystemError
		(
		);

		
	// usage demo
	public static void main(String[] args)
	{
		int success;
		IntByReference pWord = new IntByReference( 0 );

		System.out.println( "Hallo easy:" );
		
		success = Set_UserWaitingTime( 500 );
		System.out.println( "Set_UserWaitingTime( 500 ) => " + success );
		
		success = Get_UserWaitingTime( pWord );
		System.out.println( "Get_UserWaitingTime( ) => " + pWord.getValue() );

		success = Open_ComPort( (byte)1 , 19200 );
		System.out.println( "OpenComPort( 1, 19200 ) => " + success );

		if ( success == 0 )
		{
			byte NT0 = 0;
			byte bData[] = new byte[80];

			ByteByReference pError = new ByteByReference( (byte)0 );
			ByteByReference pYY = new ByteByReference( (byte)0 );
			ByteByReference pMM = new ByteByReference( (byte)0 );
			ByteByReference pDD = new ByteByReference( (byte)0 );
			ByteByReference pHH = new ByteByReference( (byte)0 );
			ByteByReference pMIN = new ByteByReference( (byte)0 );

			success = GetCurrent_Baudrate( pWord );
			if ( success == 0 )
				System.out.println( "GetCurrent_Baudrate( ) => " + pWord.getValue() );

			success = Read_Clock( NT0, pYY, pMM, pDD, pHH, pMIN );
			if ( success == 0 )
			{
				java.util.GregorianCalendar dt = new java.util.GregorianCalendar();
				java.text.DateFormat dtFormatter = java.text.SimpleDateFormat.getDateTimeInstance( java.text.SimpleDateFormat.MEDIUM, java.text.SimpleDateFormat.SHORT );
				
				dt.set( 2000+pYY.getValue(), pMM.getValue()-1, pDD.getValue(), pHH.getValue(), pMIN.getValue() );
				System.out.println( "Read_Clock(...) => " + dtFormatter.format( dt.getTime() ) );
			}
			else
			{
				System.out.println( "Read_Clock(...) => error " + success );
			}

			success = Read_Object_Value( NT0, (byte)0, (short)0, bData );
			if ( success == 0 )
			{
				System.out.printf( "MC_Read_Object_Value(I) => hex %02X%02X \n", bData[1], bData[0] );
			}
			else
			{
				System.out.println( "Read_Object_Value(I) => error " + success );
			}

		
			success = Stop_Program( NT0, pError );
			System.out.println( "Stop_Program(...) => " + success );

			success = Start_Program( NT0, pError );
			System.out.println( "Start_Program(...) => " + success );
		
			success = Lock_Device( NT0, pError );
			if ( success != 0 )
			System.out.println( "Lock_Device(...) => " + success );

			success = Unlock_Device( NT0, "123456", pError );
			System.out.println( "Unlock_Device(...) => " + success );
			
			success = Close_ComPort( );
			System.out.println( "Close_ComPort( ) => " + success );
		}
		
		success = Open_EthernetPort( "192.168.1.162", 10001, 38400, (byte)0 );
		System.out.println( "Open_EthernetPort(...) => " + success );
			
		success = Close_EthernetPort();
		System.out.println( "Close_EthernetPort(...) => " + success );
	}
}
