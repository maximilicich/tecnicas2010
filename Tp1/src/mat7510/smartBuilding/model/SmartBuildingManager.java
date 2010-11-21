package mat7510.smartBuilding.model;

import java.util.Set;

import mat7510.eventManagerApi.version2.EventManager;


/**
 * 
 * @author Grupo 10 
 *
 */
public class SmartBuildingManager implements DeviceEventListener {

	private EventManager eventManager;

	public SmartBuildingManager() {
		eventManager = EventManager.getInstance(); 
	}
	
	public void registerDeviceDriver(DeviceDriver deviceDriver) {
		if (deviceDriver == null)
			throw new IllegalArgumentException("Cannot register a null device driver in SmartBuildingManager");
		deviceDriver.addEventListener(this);
	}

	public void registerRule(Rule rule) {
		if (rule == null)
			throw new IllegalArgumentException("Cannot register a null Rule in SmartBuildingManager");
		if (rule.getEventChain() == null)
			throw new IllegalArgumentException("Cannot register in SmartBuildingManager a Rule that has no event chain");
		
		this.eventManager.registerEventChain(rule.getEventChain());
	}
	
	@Override
	public void eventOccurred(DeviceEvent e) {
		this.eventManager.eventOccurred(e);
	}
	
	public Set<DeviceDriver> getDeviceDrivers() throws SmartBuildingException {
		return DeviceDriverDAO.getInstance().getDeviceDrivers();
	}
	
	public void loadConfig() {
		
	}
}
