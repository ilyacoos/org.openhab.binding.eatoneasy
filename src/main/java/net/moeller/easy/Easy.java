package net.moeller.easy;

import java.util.HashMap;
import java.util.Map;

import com.sun.jna.ptr.PointerByReference;

public class Easy {

	public enum mode{SINGLE, MULTIPLE}
	mode md;
	Object dev;
	Map<Integer, PointerByReference> devMap = new HashMap<Integer, PointerByReference>();
	int timeout = 500;
	
	
	public Easy(mode md){
		this.md = md;
		dev = md == mode.SINGLE ? EasyCOM_API.INSTANCE : EasyCOM_MC_API.INSTANCE;
	}
	
	
	public void connect(int comPort, int baudeRate){
		setTimeout(this.timeout);
		
		int retVal;
		if(md == mode.SINGLE) {
			retVal = ((EasyCOM_API)dev).Open_ComPort( (byte)comPort, baudeRate );
		} else {
			PointerByReference pHandle = new PointerByReference();
			retVal = ((EasyCOM_MC_API)dev).MC_Open_ComPort( pHandle, (byte)comPort, baudeRate );
			devMap.put(devMap.size(), pHandle);
		}
		
		if(retVal > 0 && retVal != 4) throw new EasyException("Open_ComPort", retVal);
		if(retVal == 4) System.out.println("Successfully connected, but device - locked.");
	}
	
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
		if(md == mode.SINGLE)
			((EasyCOM_API)dev).Set_UserWaitingTime( timeout );
		else
			((EasyCOM_MC_API)dev).MC_Set_UserWaitingTime( timeout );
	}
	
	
	public void disconnect(){
		int retVal;
		if(md == mode.SINGLE) {
			retVal = ((EasyCOM_API)dev).Close_ComPort( );
		} else {
			retVal = ((EasyCOM_MC_API)dev).MC_Close_ComPort( devMap.get(0).getValue() );
		}
		
		if(retVal > 0) throw new EasyException("Close_ComPort: unsuccessfull");
	}
	
	
	public byte[] read(int netId, int object, int index){
		int retVal;
		byte bData[] = new byte[80];
		if(md == mode.SINGLE) {
			retVal = ((EasyCOM_API)dev).Read_Object_Value( (byte)netId, (byte)object, (short)index, bData );
		} else {
			retVal = ((EasyCOM_MC_API)dev).MC_Read_Object_Value( devMap.get(0).getValue(), (byte)netId, (byte)object, (short)index, (byte)0, bData );
		}
		
		if(retVal > 0) throw new EasyException("Read_Object_Value", retVal);
		
		return bData;
	}
	
	
	public void write(int netId, int object, int index, byte[] value, short length){
		int retVal;
		if(md == mode.SINGLE) {
			retVal = ((EasyCOM_API)dev).Write_Object_Value( (byte)netId, (byte)object, (short)index, (byte)length, value );
		} else {
			retVal = ((EasyCOM_MC_API)dev).MC_Write_Object_Value( devMap.get(0).getValue(), (byte)netId, (byte)object, (short)index, (byte)length, value );
		}
		
		if(retVal > 0) throw new EasyException("Write_Object_Value", retVal);
	}
}
