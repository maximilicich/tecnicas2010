package mat7510.smartBuilding.test;

import java.util.Set;

import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.DeviceEvent;
import mat7510.smartBuilding.model.Rule;
import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuilding.model.dao.RuleDAO;
import mat7510.smartBuilding.model.dao.implement.DAOFactory;
import mat7510.smartBuildingDriverAC.DeviceDriverAC;

public class RuleDAOTest_addNewRule {

	public static void main(String[] args) throws SmartBuildingException {

		RuleDAO ruleDAO = DAOFactory.getInstance().createRuleDAO();
		
		Rule newRule = new Rule.Builder("RULE-PRUEBA-TEST", "Rule dada de alta desde la clase test RuleDAOTest_addNewRule").build();
		DeviceDriver ac = new DeviceDriverAC("AC-PISO24-0358", "Aire Acond Bs As");
		newRule.setDeviceAction(ac.getDeviceActionByName("TURN ON AC"));
		
		newRule.addDeviceEvent(ac.getDeviceEventByName("FUNCTION AC WARM"));
		newRule.addDeviceEvent(ac.getDeviceEventByName("TURN ON AC"));
	
		Set<Rule> rules = ruleDAO.getRules();
		rules.add(newRule);
		
		ruleDAO.setRules(rules);
		

		rules = ruleDAO.getRules();
		for (Rule rule : rules) {
			System.out.println(rule);
			System.out.println("\t" + rule.getDeviceAction());
			for (DeviceEvent deviceEvent : rule.getDeviceEvents()) {
				System.out.println("\t\t" + deviceEvent);
			}
			System.out.println("");
		}

		
	}
	
}
