package mat7510.smartBuildingDriverClock;

import mat7510.eventManagerApi.version2.EventListener;
import mat7510.smartBuilding.model.DeviceAction;

public abstract class DeviceActionClock implements DeviceAction{

	
	private DeviceDriverClock deviceDriverClock;
	private String actionName;
	
	private String attr;
	private String value;
	private DeviceEventClock event;
	
	public DeviceActionClock ( DeviceDriverClock deviceDriverClock,
								String actionName, 
								String attr,
								String value,
								DeviceEventClock event) {
		
		if (deviceDriverClock == null) 
			throw new NullPointerException("Clock can�t be null");
		this.deviceDriverClock = deviceDriverClock;
		this.actionName = actionName;
		this.attr = attr;
		this.value = value;
		this.event = event;
		
	}
	
	@Override
	public void execute() {	
		changeClockTime();	
		for (EventListener listener : deviceDriverClock.getEventListeners()) {
			listener.eventOccurred(event);
		}
	}

	@Override
	public String getActionName() {
		return actionName;
	}
	
	public abstract void changeClockTime ( );
	
	public void setDeviceDriverClock(DeviceDriverClock deviceDriverClock) {
		this.deviceDriverClock = deviceDriverClock;
	}

	public DeviceDriverClock getDeviceDriverClock() {
		return deviceDriverClock;
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

	public void setEvent(DeviceEventClock event) {
		this.event = event;
	}

	public DeviceEventClock getEvent() {
		return event;
	}


}