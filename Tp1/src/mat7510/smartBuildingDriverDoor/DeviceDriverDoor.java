package mat7510.smartBuildingDriverDoor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mat7510.smartBuilding.model.devicedriver.DeviceAction;
import mat7510.smartBuilding.model.devicedriver.DeviceDriver;
import mat7510.smartBuilding.model.devicedriver.DeviceEvent;

/**
 * Driver de Mockup
 * 
 * @author Grupo 10
 *
 */
public class DeviceDriverDoor extends DeviceDriver {

	private Map<String, String> state = new LinkedHashMap<String, String>();
	private List<DeviceAction> actions = new ArrayList<DeviceAction>();
	private List<DeviceEvent> events = new ArrayList<DeviceEvent>();
	
	static final String ATTR_OPEN_STATE = "OpenState";
	
	static final String ATTR_VALUE_OPENED = "OPENED";
	static final String ATTR_VALUE_CLOSED = "CLOSED";
	
	static final String ATTR_LOCK_STATE = "LockState";

	static final String ATTR_VALUE_LOCKED = "LOCKED";
	static final String ATTR_VALUE_UNLOCKED = "UNLOCKED";
	
	static int ic = 0;
	int nr;


	
	public DeviceDriverDoor(String deviceID, String deviceDescription) {
		
		super(deviceID, deviceDescription);

		nr = ++ic;
		
		this.state.put(ATTR_OPEN_STATE, ATTR_VALUE_CLOSED);
		this.state.put(ATTR_LOCK_STATE, ATTR_VALUE_LOCKED);

		DeviceEventDoor deviceEventDoorOpened = new DeviceEventDoor(this, "Door Opened");
		DeviceEventDoor deviceEventDoorClosed = new DeviceEventDoor(this, "Door Closed");
		DeviceEventDoor deviceEventDoorLocked = new DeviceEventDoor(this, "Door Locked");
		DeviceEventDoor deviceEventDoorUnlocked = new DeviceEventDoor(this, "Door Unlocked");
		
		this.events.add(deviceEventDoorOpened);
		this.events.add(deviceEventDoorClosed);
		this.events.add(deviceEventDoorLocked);
		this.events.add(deviceEventDoorUnlocked);
		
		this.actions.add(new DeviceActionDoor(this, "open", ATTR_OPEN_STATE, ATTR_VALUE_OPENED, deviceEventDoorOpened));
		this.actions.add(new DeviceActionDoor(this, "close", ATTR_OPEN_STATE, ATTR_VALUE_CLOSED, deviceEventDoorClosed));
		this.actions.add(new DeviceActionDoor(this, "lock", ATTR_LOCK_STATE, ATTR_VALUE_LOCKED, deviceEventDoorLocked));
		this.actions.add(new DeviceActionDoor(this, "unlock", ATTR_LOCK_STATE, ATTR_VALUE_UNLOCKED, deviceEventDoorUnlocked));
		
		System.out.println("instanciando puerta " + deviceID + " : " + nr);
	}

	
	@Override
	public List<DeviceAction> getActions() {
		return this.actions;
	}

	@Override
	public List<DeviceEvent> getEvents() {
		return this.events;
	}

	@Override
	public Map<String, String> getState() {
		
		System.out.println("consultando estado puerta" + getDeviceID() + " : " + nr);
		
		return this.state;
	}

	@Override
	public DeviceAction getDeviceActionByName(String deviceActionName) {
		for (DeviceAction deviceAction : actions) {
			if (deviceAction.getActionName().equals(deviceActionName)) {
				return deviceAction;
			}
		}
		return null;
	}
	
	@Override
	public DeviceEvent getDeviceEventByName(String deviceEventName) {
		for (DeviceEvent deviceEvent : events) {
			if (deviceEvent.getEventName().equals(deviceEventName)) {
				return deviceEvent;
			}
		}
		return null;
	}

	
}
