package mat7510.smartBuildingDriverDoor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mat7510.smartBuilding.DeviceAction;
import mat7510.smartBuilding.DeviceDriver;
import mat7510.smartBuilding.DeviceEvent;

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


	
	public DeviceDriverDoor(String deviceID, String deviceDescription) {
		
		super(deviceID, deviceDescription);
		
		this.state.put(ATTR_OPEN_STATE, ATTR_VALUE_CLOSED);
		this.state.put(ATTR_LOCK_STATE, ATTR_VALUE_LOCKED);

		DeviceEventDoor deviceEventDoorOpened = new DeviceEventDoorOpened(this);
		DeviceEventDoor deviceEventDoorClosed = new DeviceEventDoorClosed(this);
		DeviceEventDoor deviceEventDoorLocked = new DeviceEventDoorLocked(this);
		DeviceEventDoor deviceEventDoorUnlocked = new DeviceEventDoorUnlocked(this);
		
		this.events.add(deviceEventDoorOpened);
		this.events.add(deviceEventDoorClosed);
		this.events.add(deviceEventDoorLocked);
		this.events.add(deviceEventDoorUnlocked);
		
		this.actions.add(new DeviceActionDoor(this, "Open Door", ATTR_OPEN_STATE, ATTR_VALUE_OPENED, deviceEventDoorOpened));
		this.actions.add(new DeviceActionDoor(this, "Close Door", ATTR_OPEN_STATE, ATTR_VALUE_CLOSED, deviceEventDoorClosed));
		this.actions.add(new DeviceActionDoor(this, "Lock Door", ATTR_LOCK_STATE, ATTR_VALUE_LOCKED, deviceEventDoorLocked));
		this.actions.add(new DeviceActionDoor(this, "Unlock Door", ATTR_LOCK_STATE, ATTR_VALUE_UNLOCKED, deviceEventDoorUnlocked));
		
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
		return this.state;
	}


}
