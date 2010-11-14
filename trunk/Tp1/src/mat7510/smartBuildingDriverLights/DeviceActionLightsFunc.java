package mat7510.smartBuildingDriverLights;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mat7510.smartBuilding.DeviceAction;

public abstract class DeviceActionLightsFunc implements DeviceAction{

	
	private DeviceDriverLights deviceDriverLights;
	private String actionName;
	
	public DeviceActionLightsFunc ( DeviceDriverLights deviceDriverLights){
		
		this.setDeviceDriverLights(deviceDriverLights);
		actionName = "FuncLights";
		
	}
	
	@Override
	public void execute() {
		
		Map<String,String> stateMap = getDeviceDriverLights().getState();
		
		Iterator<Entry<String, String>> itStateMap = stateMap.entrySet().iterator();
		
		while (itStateMap.hasNext()) {
			Map.Entry<String, String> elem = (Map.Entry<String, String>)itStateMap.next();
			if ( this.getActionName() == elem.getKey()){
				changeLightsFunc();
			}
		}
	
	}

	@Override
	public String getActionName() {
		return actionName;
	}
	
	public abstract void changeLightsFunc ( );
	
	public void setDeviceDriverLights(DeviceDriverLights deviceDriverLights) {
		this.deviceDriverLights = deviceDriverLights;
	}

	public DeviceDriverLights getDeviceDriverLights() {
		return deviceDriverLights;
	}


}
