
package mat7510.smartBuilding.model;

import java.util.ArrayList;
import java.util.List;

import mat7510.eventManagerApi.version2.EventManager;


/**
 * 
 * @author Grupo 10 
 *
 */
public class SmartBuildingManager implements DeviceEventListener {

	private EventManager eventManager;
	private List<DeviceDriver> drivers = new ArrayList<DeviceDriver>();;

	public SmartBuildingManager() {
		this.setEventManager(EventManager.getInstance()); 
	}
	
	public void saveConfig() {
		
	}
	
	public void loadConfig() {
		
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public void setDrivers(List<DeviceDriver> drivers) {
		this.drivers = drivers;
	}

	public List<DeviceDriver> getDrivers() {
		return drivers;
	}
	
	public void setNewDriver (DeviceDriver driver){
		this.drivers.add(driver);
	}

	@Override
	public void eventOccurred(DeviceEvent e) {
		this.eventManager.eventOccurred(e);
	}
	
	
}
