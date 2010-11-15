package mat7510.smartBuildingDriverDoor;

import mat7510.eventManagerApi.version2.Event;

/**
 * 
 * @author Grupo 10 
 *
 */
public class DeviceEventDoorOpened extends DeviceEventDoor  {

	
	public DeviceEventDoorOpened(DeviceDriverDoor door) {
		super(door);
	}

	@Override
	public String getEventName() {
		return "Door Opened";
	}

	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventDoorOpened))
			return false;
		// El evento sera el mismo si la puerta es la misma:
		return (((DeviceEventDoorOpened)anotherEvent).getDoor().getDeviceID().equals(this.getDoor().getDeviceID()));
	}

}
