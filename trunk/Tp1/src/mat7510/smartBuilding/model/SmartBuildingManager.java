package mat7510.smartBuilding.model;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * 
 * @author Grupo 10 
 *
 */
public class SmartBuildingManager {

	private static SmartBuildingManager instance = new SmartBuildingManager();

	private SmartBuildingEventEngine eventEngine;
	
	private Set<DeviceDriver> deviceDrivers = new LinkedHashSet<DeviceDriver>();
	private Set<Rule> rules = new LinkedHashSet<Rule>();
	
	private SmartBuildingManager() {
		this.eventEngine = SmartBuildingEventEngine.getInstance();
	}
	public static SmartBuildingManager getInstance() {
		return instance;
	}
	
	/**
	 * @throws SmartBuildingException 
	 * 
	 */
	public void loadConfig() throws SmartBuildingException {
		
		loadDeviceDriversConfig();
		loadRulesConfig();
		
	}
	
	/**
	 * 
	 * @throws SmartBuildingException
	 */
	public void refreshDeviceDrivers() throws SmartBuildingException {
		loadDeviceDriversConfig();
	}
	
	
	/**
	 * Devuelve los DeviceDrivers configurados
	 * OJO! Estan en memoria...no se actualiza de la persistencia
	 * Hasta que se hace un refreshDrivers()
	 * O si se hace un loadConfig()
	 * 
	 * @return
	 */
	public Set<DeviceDriver> getDeviceDrivers() {
		return deviceDrivers;
	}
	

	/**
	 * Devuelve las Rules configuradas
	 * OJO! Estan en memoria...no se actualiza
	 * Salvo que se haga un loadConfig()
	 *  
	 * @return
	 */
	public Set<Rule> getRules() {
		return rules;
	}
	
	
	/**
	 * 
	 * @param newRule
	 */
	public void addRule(Rule newRule) {
		
		// Validaciones
			// not null
			// datos obligatorios

		// Agregamos a nuestro SET
			// duplicacion de ID : Si no entra, es pq el ID es duplicado
		
		// Alta en el DAO
		
		// Registramos en el EventEngine
		
	}
	
	
	/**
	 * 
	 * @param ruleID
	 */
	public void disableRule(String ruleID) {
		
	}
	
	
	/**
	 * 
	 * @param ruleID
	 */
	public void enableRule(String ruleID) {
		
	}
	
	
	/**
	 * 
	 * @param ruleID
	 */
	public void deleteRule(String ruleID) {
		
	}
	
	
	/**
	 * Se cargan los Devices de persistencia
	 * Y se registran en el EventEngine 
	 * El EventEngine se setea como LISTENER de cada Device
	 * De manera que cada uno informe los eventos al Motor
	 * 
	 * @throws SmartBuildingException 
	 * 
	 */
	private void loadDeviceDriversConfig() throws SmartBuildingException {

		deviceDrivers = DeviceDriverDAO.getInstance().getDeviceDrivers();

		for (DeviceDriver deviceDriver : deviceDrivers) {
			eventEngine.registerDeviceDriver(deviceDriver);
		}
	}

	
	/**
	 * @throws SmartBuildingException  
	 * 
	 */
	private void loadRulesConfig() throws SmartBuildingException {

		eventEngine.reset();
		
		rules = RuleDAO.getInstance().getRules();
		
		for (Rule rule : rules) {
			eventEngine.registerRule(rule);
		}

	}
}
