package mat7510.smartBuilding.model.dao.implement;

import mat7510.smartBuilding.model.dao.DeviceDriverDAO;
import mat7510.smartBuilding.model.dao.RuleDAO;

/**
 * FACTORY de los DAO
 * Determina la clase concreta de DAO a utilizar 

 * Singleton

 * 
 * @author Grupo 10
 *
 */
public class DAOFactory {

	private static DAOFactory instance = new DAOFactory();
	
	private DAOFactory() {
	}
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public DeviceDriverDAO createDeviceDriverDAO() {
		return new DeviceDriverDAOXMLImplementation();
	}

	public RuleDAO createRuleDAO() {
		return new RuleDAOXMLImplementation();
	}
	
}
