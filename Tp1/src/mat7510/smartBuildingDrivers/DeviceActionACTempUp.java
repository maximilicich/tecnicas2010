package mat7510.smartBuildingDrivers;

import java.util.Map;


public class DeviceActionACTempUp extends DeviceActionACTemp{
	

	public DeviceActionACTempUp ( DeviceDriverAC deviceDriverAC){
		super(deviceDriverAC);
		
	}

	@Override
	public void modifyTemp (Map.Entry<String, String> mapEntry){
		
		String value;
		
		if ( this.getActionName() == mapEntry.getKey() && mapEntry.getValue() != getDeviceDriverAC().getTempMax()){
			 value = String.valueOf(Integer.parseInt(mapEntry.getValue()) + 1);
			 getDeviceDriverAC().setMapEntry(getActionName(), value);
		}
	}
	
}
