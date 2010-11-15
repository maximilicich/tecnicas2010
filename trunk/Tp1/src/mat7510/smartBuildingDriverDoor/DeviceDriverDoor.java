package mat7510.smartBuildingDriverDoor;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mat7510.eventManagerApi.version2.EventListener;
import mat7510.smartBuilding.DeviceAction;
import mat7510.smartBuilding.DeviceDriver;
import mat7510.smartBuilding.DeviceEvent;

/**
 * Driver de Mockup
 * 
 * @author Grupo 10
 *
 */
public class DeviceDriverDoor implements DeviceDriver {

	private Set<EventListener> eventListeners = new LinkedHashSet<EventListener>();
	private Map<String, String> state = new LinkedHashMap<String, String>();
	
	
	public DeviceDriverDoor() {
		
		this.state.put("OpenState", "CLOSED");
		this.state.put("LockState", "LOCKED");

	}
	
	@Override
	public void addEventListener(EventListener eventListener) {
		eventListeners.add(eventListener);

	}

	@Override
	public List<DeviceAction> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceEvent> getEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getState() {
		return this.state;
	}

	@Override
	public Set<EventListener> getEventListeners() {
		return this.eventListeners;
	}

}
