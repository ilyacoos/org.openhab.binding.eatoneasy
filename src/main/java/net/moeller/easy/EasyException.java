package net.moeller.easy;

import java.util.HashMap;

public class EasyException extends RuntimeException {
	private static HashMap<String, String[]> errors = new HashMap<String, String[]>();
	static{
		String[] Open_ComPort = {
				"Function call up successful.",
				"A parameter contains an invalid value.",
				"Device sends an error message.",
				"Device does not respond.",
				"The device’s interface is protected with a password. Call up Unlock_Device!",
				"Device type is unknown.",
				"Error message from Windows. Interrogate error code with GetLastSystemError.",
				"",
				"The COM port is not present or not registered in the system at the moment (connection break)",
				"The COM port is blocked by another process or is not registered in the system (connection break).",
				"General communication error occurred (possible hardware failure)",
				"Internal error of EASY_COM.dll"
		};
		errors.put("Open_ComPort", Open_ComPort);
		
		String[] RW_Object_Value = {
				"Function call up successful.",
				"A parameter contains an invalid value.",
				"Device sends an error message.",
				"Device does not respond.",
				"",
				"",
				"Error message from Windows. Interrogate error code with GetLastSystemError.",
				"No connection open.",
				"The COM port is no longer present or no longer registered in the system (connection break)",
				"The COM port is blocked by another process or is no longer registered in the system (connection break).",
				"General communication error occurred (possible hardware failure)",
				"Internal error of EASY_COM.dll"
		};
		errors.put("Read_Object_Value", RW_Object_Value);
		errors.put("Write_Object_Value", RW_Object_Value);
	}
	
	public EasyException(String callback, int RetVal){
		super( callback + ": " + errors.get(callback)[RetVal] );
	}
	
	public EasyException() {
	}

	public EasyException(String message) {
		super(message);
	}

	public EasyException(Throwable cause) {
		super(cause);
	}

	public EasyException(String message, Throwable cause) {
		super(message, cause);
	}

	public EasyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
