package mat7510.smartBuilding.service;

import java.util.LinkedHashSet;
import java.util.Set;

import mat7510.smartBuilding.dao.DAOFactory;
import mat7510.smartBuilding.dao.DeviceDriverDAO;
import mat7510.smartBuilding.dao.RuleDAO;
import mat7510.smartBuilding.domain.Rule;
import mat7510.smartBuilding.domain.devicedriver.DeviceDriver;
import mat7510.smartBuilding.exception.SmartBuildingException;


/**
 * 
 * @author Grupo 10 
 *
 */
public class SmartBuildingManager {

	private SmartBuildingEventEngine eventEngine;
	
	private Set<DeviceDriver> deviceDrivers = new LinkedHashSet<DeviceDriver>();
	private Set<Rule> rules = new LinkedHashSet<Rule>();

	/**
	 * Los DAO
	 */
	private DeviceDriverDAO deviceDriverDAO;
	private RuleDAO ruleDAO;
	
	public SmartBuildingManager(DAOFactory daoFactory) {
		this.eventEngine = SmartBuildingEventEngine.getInstance();
		this.ruleDAO = daoFactory.createRuleDAO();
		this.deviceDriverDAO = daoFactory.createDeviceDriverDAO();
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
		ruleDAO.setRules(rules);
		
		// Registramos en el EventEngine
		eventEngine.registerRule(newRule);
	
	}


	/**
	 * 
	 * @param newRule
	 * @throws SmartBuildingException 
	 */
	public void addDeviceDriver(DeviceDriver newDeviceDriver) throws SmartBuildingException {

		// TODO Esto deberia ser Transaccional
		
		// Validaciones
		
		// not null
		if (newDeviceDriver == null)
			throw new IllegalArgumentException("Cannot add null Device.");

		// El ID es validado en el constructor de la misma Rule...
		
		// Agregamos a nuestro SET
		if (!deviceDrivers.add(newDeviceDriver))
			throw new SmartBuildingException("Cannot add new DeviceDriver because ID already exists (ID = " + newDeviceDriver.getDeviceID() + ")");
			
		
		// Alta en el DAO : registramos todo de nuevo
		// deviceDriverDAO.setDeviceDrivers(deviceDrivers);
		
		// NO! No registramos todo de nuevo...sino, hacemos macana:
		deviceDriverDAO.addDeviceDriver(newDeviceDriver);
		
		// Registramos en el EventEngine
		eventEngine.registerDeviceDriver(newDeviceDriver);

	
	}


	
	/**
	 * 
	 * @param ruleID
	 * @throws SmartBuildingException 
	 */
	public void disableRule(String ruleID) throws SmartBuildingException {
		
		// Buscamos la Rule de ese ID
		Rule ruleAffected = findRuleInOurSet(ruleID);
		if (ruleAffected == null)
			throw new SmartBuildingException("Cannot enable Rule for ID " + ruleID + " because there is not Rule with such ID.");
		
		// La habilitamos
		ruleAffected.setEnabled(false);
		
		// Y actualizamos la persistencia
		ruleDAO.setRules(rules);
		
	}
	
	
	/**
	 * 
	 * @param ruleID
	 * @throws SmartBuildingException 
	 */
	public void enableRule(String ruleID) throws SmartBuildingException {

		// Buscamos la Rule de ese ID
		Rule ruleAffected = findRuleInOurSet(ruleID);
		if (ruleAffected == null)
			throw new SmartBuildingException("Cannot enable Rule for ID " + ruleID + " because there is not Rule with such ID.");
		
		// La habilitamos
		ruleAffected.setEnabled(true);
		
		// Y actualizamos la persistencia
		ruleDAO.setRules(rules);
	}
	
	
	/**
	 * 
	 * @param ruleID
	 * @throws SmartBuildingException 
	 */
	public void deleteRule(String ruleID) throws SmartBuildingException {
		
		if (ruleID == null || ruleID.trim().equalsIgnoreCase("")) 
			throw new IllegalArgumentException("invalid ruleID (must not be null or blank)");
		
		// El ID debe estar en nuestras reglas. Sino EXCEPTION (esta bien asi??)
		Rule ruleToDelete = null;
		for (Rule rule : rules) {
			if (rule.getRuleID().trim().equalsIgnoreCase(ruleID.trim())) {
				ruleToDelete = rule;
				break;
			}
		}
		if (ruleToDelete == null)
			throw new SmartBuildingException("Cannot delete Rule for ID " + ruleID + " because there is not Rule with such ID." );
		
		// Luego lo retiramos de nuestro Set
		if (!rules.remove(ruleToDelete)) 
			throw new SmartBuildingException("Could not delete Rule for ID" + ruleID + " because it does not exist in SmartBuildingManager Rules Set.");
		
		// Grabamos el Set en el DAO (persistencia)
		ruleDAO.setRules(rules);
		
		// Y DESREGISTRAMOS LA RULE DEL EVENTENGINE
		eventEngine.unregisterRule(ruleToDelete);
		
		
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

		deviceDrivers = deviceDriverDAO.getDeviceDrivers();

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
		
		rules = ruleDAO.getRules();
		
		for (Rule rule : rules) {
			eventEngine.registerRule(rule);
		}
	}
	
	
	/**
	 * 
	 * @param ruleID
	 * @return
	 */
	private Rule findRuleInOurSet(String ruleID) {

		Rule ruleFound = null;
		for (Rule rule : rules) {
			if (rule.getRuleID().trim().equalsIgnoreCase(ruleID.trim())) {
				ruleFound = rule;
				break;
			}
		}
		return ruleFound;
	}
	
}
