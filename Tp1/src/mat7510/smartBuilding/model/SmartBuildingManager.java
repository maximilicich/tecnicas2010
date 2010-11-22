package mat7510.smartBuilding.model;

import java.util.LinkedHashSet;
import java.util.Set;

import mat7510.smartBuilding.model.Rule.VoidAction;


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
	 * @throws SmartBuildingException 
	 */
	public void addRule(Rule newRule) throws SmartBuildingException {

		int count = 1;
		boolean insert = false;
		
		if (newRule == null){
			throw new SmartBuildingException(" Regla vacia "); 	
		}
		// Validaciones
		if ( newRule.getDeviceAction()==null || newRule.getRuleID() == null || newRule.getDeviceEvents() == null){
			throw new SmartBuildingException(" No se encuentran cargados todos los datos obligatorios para la nueva regla ");
		}

		// Agregamos a nuestro SET
		while ( insert == false){
			
			if ( rules.add(newRule) == false) {
				if (count == 0){
					newRule.setRuleID(newRule.getRuleID() + "copy" + count);
				}
				else{
					newRule.setRuleID(newRule.getRuleID() + count);
				}
				count = count + 1;
			}
			else {
				insert = true;
			}
		}
		eventEngine.registerRule(newRule);
	}

	
	/**
	 * 
	 * @param ruleID
	 */
	public void disableRule(String ruleID) {
		
		for (Rule rule: rules) {
			if ( rule.getRuleID() == ruleID ){
				rule.setEnabled(false);
				//Sacar la regla de eventEngine
				//Actualizar ABM
			}
		}
		
	}
	
	
	/**
	 * 
	 * @param ruleID
	 */
	public void enableRule(String ruleID) {
		
		
		for (Rule rule: rules) {
			if ( rule.getRuleID() == ruleID ){
				rule.setEnabled(true);
				eventEngine.registerRule(rule);
				//actualizar ABM
			}
		}
		
	}
	
	
	/**
	 * 
	 * @param ruleID
	 */
	public void deleteRule(String ruleID) {
		
		for (Rule rule: rules) {
			if ( rule.getRuleID() == ruleID ){
				//Sacar la regla de eventEngine
				//Actualizar config
			}
		}
		
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
