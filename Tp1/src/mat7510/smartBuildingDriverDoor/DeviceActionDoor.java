package mat7510.smartBuildingDriverDoor;

import mat7510.eventManagerApi.version2.EventListener;
import mat7510.smartBuilding.DeviceAction;

public class DeviceActionDoor implements DeviceAction {

	private DeviceDriverDoor door;
	private String actionName;
	private String attr;
	private String value;
	private DeviceEventDoor event;
	
	public DeviceActionDoor(DeviceDriverDoor door, 
							String actionName, 
							String attr,
							String value,
							DeviceEventDoor event) {
		if (door == null) 
			throw new NullPointerException("Door can´t be null");
		this.door = door;
		this.actionName = actionName;
		this.attr = attr;
		this.value = value;
		this.event = event;
	}
	
	@Override
	public String getActionName() {
		return this.actionName;
	}
	
	@Override
	public void execute() {
		door.getState().put(attr, value);
		for (EventListener listener : door.getEventListeners()) {
			listener.eventOccurred(event);
		}
	}

}
