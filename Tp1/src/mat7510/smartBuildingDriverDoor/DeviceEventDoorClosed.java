package mat7510.smartBuildingDriverDoor;

import mat7510.eventManagerApi.version2.Event;

/**
 * 
 * @author Grupo 10 
 *
 */
public class DeviceEventDoorClosed extends DeviceEventDoor  {

	
	public DeviceEventDoorClosed(DeviceDriverDoor door) {
		super(door, "Door Closed");
	}


	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventDoorClosed))
			return false;
		// El evento sera el mismo si la puerta es la misma:
		return (((DeviceEventDoorClosed)anotherEvent).getDoor().getDeviceID().equals(this.getDoor().getDeviceID()));
	}

}
