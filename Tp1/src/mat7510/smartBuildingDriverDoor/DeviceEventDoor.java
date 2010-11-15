package mat7510.smartBuildingDriverDoor;

import mat7510.smartBuilding.DeviceEvent;

/**
 * 
 * @author Grupo 10 
 *
 */
public abstract class DeviceEventDoor implements DeviceEvent {

	private DeviceDriverDoor door;

	public DeviceEventDoor(DeviceDriverDoor door) {
		if (door == null) 
			throw new NullPointerException("Door can´t be null");
		this.door = door;
	}

	public DeviceDriverDoor getDoor() {
		return door;
	}

}