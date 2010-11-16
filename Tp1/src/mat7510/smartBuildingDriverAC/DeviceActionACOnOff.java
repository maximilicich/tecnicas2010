package mat7510.smartBuildingDriverAC;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mat7510.eventManagerApi.version2.EventListener;
import mat7510.smartBuilding.DeviceAction;

public abstract class DeviceActionACOnOff implements DeviceAction {

	private DeviceDriverAC deviceDriverAC;
	private String actionName;

	private String attr;
	private String value;
	private DeviceEventAC event;



	public DeviceActionACOnOff ( DeviceDriverAC deviceDriverAC, 
								String actionName, 
								String attr,
								String value,
								DeviceEventAC event) {
		
		if (deviceDriverAC == null) 
			throw new NullPointerException("AC can´t be null");
		
		this.deviceDriverAC = deviceDriverAC;
		this.actionName = actionName;
		this.setAttr(attr);
		this.setValue(value);
		this.event = event;


	}

	@Override
	public void execute() {

		Map<String,String> stateMap = getDeviceDriverAC().getState();

		Iterator<Entry<String, String>> itStateMap = stateMap.entrySet().iterator();

		while (itStateMap.hasNext()) {
			Map.Entry<String, String> elem = (Map.Entry<String, String>)itStateMap.next();
			if ( this.getActionName() == elem.getKey() ){
				changeOnOff();
				
				for (EventListener listener : deviceDriverAC.getEventListeners()) {
					listener.eventOccurred(event);
				}
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

}
