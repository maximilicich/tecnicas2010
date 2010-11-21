package mat7510.smartBuildingDriverDoor;

import mat7510.smartBuilding.model.DeviceAction;
import mat7510.smartBuilding.model.DeviceEventListener;

public class DeviceActionDoor extends DeviceAction {

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
		
		super(door, actionName);
		
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
		for (DeviceEventListener listener : door.getEventListeners()) {
			listener.eventOccurred(event);
		}
	}

}
