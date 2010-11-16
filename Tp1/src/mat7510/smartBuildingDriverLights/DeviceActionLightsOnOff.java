package mat7510.smartBuildingDriverLights;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mat7510.smartBuilding.DeviceAction;

public abstract class DeviceActionLightsOnOff implements DeviceAction {

	private DeviceDriverLights deviceDriverLights;
	private String actionName;
	

	private String attr;
	private String value;
	private DeviceEventLights event;
	
	public DeviceActionLightsOnOff ( DeviceDriverLights deviceDriverLights, 
										String actionName, 
										String attr,
										String value,
										DeviceEventLights event) {

		if (deviceDriverLights == null) 
			throw new NullPointerException("Lights can´t be null");

		this.deviceDriverLights = deviceDriverLights;
		this.actionName = actionName;
		this.attr = attr;
		this.value = value;
		this.event = event;
		
	}

	@Override
	public void execute() {
		
		Map<String,String> stateMap = getDeviceDriverLights().getState();
		
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
	
	
	public void setDeviceDriverLights(DeviceDriverLights deviceDriverLights) {
		this.deviceDriverLights = deviceDriverLights;
	}

	public DeviceDriverLights getDeviceDriverLights() {
		return deviceDriverLights;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getAttr() {
		return attr;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setEvent(DeviceEventLights event) {
		this.event = event;
	}

	public DeviceEventLights getEvent() {
		return event;
	}

}
