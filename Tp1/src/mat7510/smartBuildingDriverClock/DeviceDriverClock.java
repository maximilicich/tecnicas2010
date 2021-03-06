package mat7510.smartBuildingDriverClock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mat7510.smartBuilding.domain.devicedriver.DeviceAction;
import mat7510.smartBuilding.domain.devicedriver.DeviceDriver;
import mat7510.smartBuilding.domain.devicedriver.DeviceEvent;

public class DeviceDriverClock extends DeviceDriver{
	
	private List<DeviceAction> deviceActions = new ArrayList<DeviceAction>();
	private List<DeviceEvent>  deviceEvents	 = new ArrayList<DeviceEvent>(); 
	private Map<String,String> stateMap		 = new LinkedHashMap<String, String>();
	
	static final String ATTR_TIME_STATE = "DAY TIME";
	static final String ATTR_VALUE_MOORNING = "MOORNING";
	static final String ATTR_VALUE_AFTERNOON = "AFTERNOON";
	static final String ATTR_VALUE_EVENING = "EVENING";

	
	public DeviceDriverClock(String deviceID, String deviceDescription){
		
		super(deviceID, deviceDescription);
		
		stateMap.put(ATTR_TIME_STATE, ATTR_VALUE_MOORNING);
		
		DeviceEventClock deviceEventClockMoorning  = new DeviceEventClockMorning(this);
		DeviceEventClock deviceEventClockAfternoon = new DeviceEventClockAfternoon(this);
		DeviceEventClock deviceEventClockEvening   = new DeviceEventClockEvening(this);
		
		this.deviceEvents.add(deviceEventClockMoorning);
		this.deviceEvents.add(deviceEventClockAfternoon);
		this.deviceEvents.add(deviceEventClockEvening);
		
		this.deviceActions.add(new DeviceActionClockMorning(this,deviceEventClockMoorning));
		this.deviceActions.add(new DeviceActionClockAfternoon(this,deviceEventClockAfternoon));
		this.deviceActions.add(new DeviceActionClockEvening(this,deviceEventClockEvening));
		
	}

	public List<DeviceAction> getActions() {
		return deviceActions;
	}

	public List<DeviceEvent> getEvents() {
		return deviceEvents;
	}

	public Map<String, String> getState() {
		return stateMap;
	}

	
	public void setAction( DeviceAction deviceAction ) {
		
		deviceActions.add(deviceAction);
		
	}
	
	public void setEvent( DeviceEvent deviceEvent ) {
		
		deviceEvents.add(deviceEvent);
		
	}
	
	public void setMapEntry ( String key, String value ) {
		
		stateMap.put(key, value);
		
	}
	
	@Override
	public DeviceAction getDeviceActionByName(String deviceActionName) {
		for (DeviceAction deviceAction : deviceActions) {
			if (deviceAction.getActionName().equals(deviceActionName)) {
				return deviceAction;
			}
		}
		return null;
	}
	
	@Override
	public DeviceEvent getDeviceEventByName(String deviceEventName) {
		for (DeviceEvent deviceEvent : deviceEvents) {
			if (deviceEvent.getEventName().equals(deviceEventName)) {
				return deviceEvent;
			}
		}
		return null;
	}


}