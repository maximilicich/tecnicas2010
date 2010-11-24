package mat7510.smartBuilding.test;

import java.util.Set;

import mat7510.smartBuilding.dao.DAOFactory;
import mat7510.smartBuilding.dao.RuleDAO;
import mat7510.smartBuilding.dao.implement.DAOFactoryXMLImplementation;
import mat7510.smartBuilding.model.DeviceEvent;
import mat7510.smartBuilding.model.Rule;
import mat7510.smartBuilding.model.SmartBuildingException;

public class RuleDAOTest_showRules {

	public static void main(String[] args) throws SmartBuildingException {
		
		DAOFactory daoFactory = new DAOFactoryXMLImplementation();
		
		RuleDAO ruleDAO = daoFactory.createRuleDAO();
		
		Set<Rule> rules = ruleDAO.getRules();
		for (Rule rule : rules) {
			System.out.println(rule);
			
			System.out.println("enabled    ? " + rule.isEnabled());
			System.out.println("ordered    ? " + rule.isOrdered());
			System.out.println("continuous ? " + rule.isContinuous());
			
			System.out.println("\t" + rule.getDeviceAction());
			for (DeviceEvent deviceEvent : rule.getDeviceEvents()) {
				System.out.println("\t\t" + deviceEvent);
			}
			System.out.println("");
		}
		
	}
	
}
