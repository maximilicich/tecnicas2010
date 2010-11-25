package mat7510.smartBuilding.dao;

import mat7510.smartBuilding.dao.implement.DAOFactoryXMLImplementation;

/**
 * FACTORY de los DAO
 * Determina la clase concreta de DAO a utilizar 

 * Singleton

 * 
 * @author Grupo 10
 *
 */
public abstract class DAOFactory {

	// List of DAO types supported by the factory
	public static final int XML = 1;
	public static final int ORACLE = 2;
	public static final int SYBASE = 3;
	public static final int MYSQL = 4;
	public static final int MSSQLSERVER = 5;

	public static DAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case XML:
			return new DAOFactoryXMLImplementation();
		case ORACLE: 
			return null;
		case SYBASE: 
			return null;      
		case MYSQL: 
			return null;
		case MSSQLSERVER: 
			return null;
		default: 
			return null;
		}
	}

	public abstract DeviceDriverDAO createDeviceDriverDAO();
	public abstract RuleDAO createRuleDAO();

}
