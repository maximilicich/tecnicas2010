package mat7510.smartBuilding.service;

import mat7510.eventManagerApi.version2.EventManager;
import mat7510.smartBuilding.domain.Rule;
import mat7510.smartBuilding.domain.devicedriver.DeviceDriver;
import mat7510.smartBuilding.domain.devicedriver.DeviceEvent;
import mat7510.smartBuilding.domain.devicedriver.DeviceEventListener;


/**
 * 
 * @author Grupo 10 
 *
 */
public class SmartBuildingEventEngine implements DeviceEventListener {

	private static SmartBuildingEventEngine instance = new SmartBuildingEventEngine();
	
	private EventManager eventManager;

	private SmartBuildingEventEngine() {
		eventManager = EventManager.getInstance(); 
	}
	public static SmartBuildingEventEngine getInstance() {
		return instance;
	}
	
	/**
	 * 
	 * @param deviceDriver
	 */
	public void registerDeviceDriver(DeviceDriver deviceDriver) {
		if (deviceDriver == null)
			throw new IllegalArgumentException("Cannot register a null device driver in SmartBuildingManager");
		deviceDriver.addEventListener(this);
	}


	/**
	 * 
	 * @param rule
	 */
	public void registerRule(Rule rule) {
		if (rule == null)
			throw new IllegalArgumentException("Cannot register a null Rule in SmartBuildingManager");
		if (rule.getEventChain() == null)
			throw new IllegalArgumentException("Cannot register in SmartBuildingManager a Rule that has no event chain");
		
		this.eventManager.registerEventChain(rule.getEventChain());
	}
	

	/**
	 * 
	 * @param rule
	 * @return
	 */
	public boolean unregisterRule(Rule rule) {
		
		return this.eventManager.unregisterEventChain(rule.getEventChain());
		
	}
	
	@Override
	public void eventOccurred(DeviceEvent e) {
		this.eventManager.eventOccurred(e);
	}
	
	public void reset() {
		this.eventManager.reset();
	}
}
