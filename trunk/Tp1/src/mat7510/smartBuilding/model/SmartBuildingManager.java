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
	 * @throws SmartBuildingException 
	 */
	public void addRule(Rule newRule) throws SmartBuildingException {

		// TODO Esto deberia ser Transaccional
		
		// Validaciones
		
		// not null
		if (newRule == null)
			throw new IllegalArgumentException("Cannot add null Rule.");

		// El ID es validado en el constructor de la misma Rule...
		
		if ( newRule.getDeviceAction() == null)
			throw new SmartBuildingException(" Cannot add Rule with no DeviceAction defined.");

		if (newRule.getDeviceEvents() == null)
			throw new SmartBuildingException(" Cannot add Rule with no DeviceEvents defined.");

		
		// Agregamos a nuestro SET
		// SI EL ID YA EXISTE: LO MODIFICAMOS PARA QUE ENTRE IGUAL
		int count = 0;
		while (! rules.add(newRule)) {
			
			if (count == 0) 
				newRule.setRuleID(newRule.getRuleID() + "copy" + count);
			else 
				newRule.setRuleID(newRule.getRuleID() + count);
			++count;
		}

		
		// Alta en el DAO : registramos todo de nuevo
		RuleDAO.getInstance().setRules(rules);
		
		// Registramos en el EventEngine
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
		
		// El ID debe estar en nuestras reglas. Sino EXCEPTION (esta bien asi??)
		
		// Luego lo retiramos de nuestro Set
		
		// Grabamos el Set en el DAO (persistencia)
		
		// Y DESREGISTRAMOS LA RULE DEL EVENTENGINE
		
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
