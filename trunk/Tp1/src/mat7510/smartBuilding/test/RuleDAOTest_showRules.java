package mat7510.smartBuilding.test;

import java.util.Set;

import mat7510.smartBuilding.model.DeviceEvent;
import mat7510.smartBuilding.model.Rule;
import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuilding.model.dao.RuleDAO;
import mat7510.smartBuilding.model.dao.implement.DAOFactory;

public class RuleDAOTest_showRules {

	public static void main(String[] args) throws SmartBuildingException {
		
		RuleDAO ruleDAO = DAOFactory.getInstance().createRuleDAO();
		
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
