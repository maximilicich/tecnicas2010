package mat7510.smartBuildingDriverAC;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mat7510.smartBuilding.DeviceAction;

public abstract class DeviceActionACOnOff implements DeviceAction {

	private DeviceDriverAC deviceDriverAC;
	private String actionName;
	
	
	public DeviceActionACOnOff ( DeviceDriverAC deviceDriverAC){
		
		this.setDeviceDriverAC(deviceDriverAC);
		actionName = "EncendidoAC";
		
	}

	@Override
	public void execute() {
		
		Map<String,String> stateMap = getDeviceDriverAC().getState();
		
		Iterator<Entry<String, String>> itStateMap = stateMap.entrySet().iterator();
		
		while (itStateMap.hasNext()) {
			Map.Entry<String, String> elem = (Map.Entry<String, String>)itStateMap.next();
			if ( this.getActionName() == elem.getKey() ){
				changeOnOff();
			}
		}
		
	}

	public abstract void changeOnOff();
		

	@Override
	public String getActionName() {
		return actionName;
	}
	
	
	public void setDeviceDriverAC(DeviceDriverAC deviceDriverAC) {
		this.deviceDriverAC = deviceDriverAC;
	}

	public DeviceDriverAC getDeviceDriverAC() {
		return deviceDriverAC;
	}

}