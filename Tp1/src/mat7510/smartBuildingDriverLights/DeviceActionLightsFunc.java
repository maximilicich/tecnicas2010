package mat7510.smartBuildingDriverLights;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mat7510.smartBuilding.domain.devicedriver.DeviceAction;
import mat7510.smartBuilding.domain.devicedriver.DeviceEventListener;

public abstract class DeviceActionLightsFunc extends DeviceAction{

	
	private DeviceDriverLights deviceDriverLights;
	private String actionName;
	
	private String attr;
	private String value;
	private DeviceEventLights event;
	
	public DeviceActionLightsFunc ( DeviceDriverLights deviceDriverLights, 
									String actionName, 
									String attr,
									String value,
									DeviceEventLights event) {
		
		super(deviceDriverLights, actionName);
		
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
			if ( this.getAttr() == elem.getKey()){
				changeLightsFunc();
				for (DeviceEventListener listener : deviceDriverLights.getEventListeners()) {
					listener.eventOccurred(event);
				}
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
