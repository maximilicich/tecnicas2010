package mat7510.smartBuildingDriverDoor;

import mat7510.smartBuilding.model.DeviceEvent;

/**
 * 
 * @author Grupo 10 
 *
 */
public abstract class DeviceEventDoor extends DeviceEvent {

	private DeviceDriverDoor door;

	public DeviceEventDoor(DeviceDriverDoor door, String eventName) {
		super(door, eventName);
		this.door = door;
	}

	public DeviceDriverDoor getDoor() {
		return door;
	}

}