package mat7510.smartBuilding.dao.implement;

import mat7510.smartBuilding.dao.DAOFactory;
import mat7510.smartBuilding.dao.DeviceDriverDAO;
import mat7510.smartBuilding.dao.RuleDAO;

/**
 * 
 * @author Grupo 10
 *
 */
public class DAOFactoryXMLImplementation extends DAOFactory {

	@Override
	public DeviceDriverDAO createDeviceDriverDAO() {
		return new DeviceDriverDAOXMLImplementation();
	}

	@Override
	public RuleDAO createRuleDAO() {
		return new RuleDAOXMLImplementation();
	}

}
