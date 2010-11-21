package mat7510.smartBuildingDriverDoor;

import mat7510.eventManagerApi.version2.Event;

/**
 * 
 * @author Grupo 10 
 *
 */
public class DeviceEventDoorLocked extends DeviceEventDoor  {

	
	public DeviceEventDoorLocked(DeviceDriverDoor door) {
		super(door, "Door Locked");
	}


	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventDoorLocked))
			return false;
		// El evento sera el mismo si la puerta es la misma:
		return (((DeviceEventDoorLocked)anotherEvent).getDoor().getDeviceID().equals(this.getDoor().getDeviceID()));
	}

}
