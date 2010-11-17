package mat7510.smartBuildingDriverAC;

import java.util.Map;


public class DeviceActionACTempDown extends DeviceActionACTemp{
	
	static final String actionName = "TEMP DOWN AC";
	static final String attr = "TEMP"; 
	static final String value = "20"; 

	public DeviceActionACTempDown(DeviceDriverAC deviceDriverAC,DeviceEventAC event) {
		super(deviceDriverAC, actionName, attr, value, event);
	}

	@Override
	public void modifyTemp (Map.Entry<String, String> mapEntry){
		
		String value;
		
		if ( this.getAttr() == mapEntry.getKey() && mapEntry.getValue() != getDeviceDriverAC().getTempMin()){
			 value = String.valueOf(Integer.parseInt(mapEntry.getValue()) - 1);
			 getDeviceDriverAC().setMapEntry(getAttr(), value);
		}
	}


}
