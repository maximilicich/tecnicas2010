package mat7510.smartBuildingDriverAC;

import java.util.Map;


public class DeviceActionACTempDown extends DeviceActionACTemp{
	
	

	public DeviceActionACTempDown(DeviceDriverAC deviceDriverAC,
								String actionName,
								String attr,
								String value,
								DeviceEventAC event) {
		
		super(deviceDriverAC, actionName, attr, value, event);
	}

	@Override
	public void modifyTemp (Map.Entry<String, String> mapEntry){
		
		String value;
		
		if ( this.getActionName() == mapEntry.getKey() && mapEntry.getValue() != getDeviceDriverAC().getTempMin()){
			 value = String.valueOf(Integer.parseInt(mapEntry.getValue()) - 1);
			 getDeviceDriverAC().setMapEntry(getActionName(), value);
		}
	}


}
