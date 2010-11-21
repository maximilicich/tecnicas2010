package mat7510.smartBuildingDriverDoor;

import mat7510.eventManagerApi.version2.Event;

/**
 * 
 * @author Grupo 10 
 *
 */
public class DeviceEventDoorUnlocked extends DeviceEventDoor  {

	
	public DeviceEventDoorUnlocked(DeviceDriverDoor door) {
		super(door, "Door Unlocked");
	}


	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventDoorUnlocked))
			return false;
		// El evento sera el mismo si la puerta es la misma:
		return (((DeviceEventDoorUnlocked)anotherEvent).getDoor().getDeviceID().equals(this.getDoor().getDeviceID()));
	}

}
