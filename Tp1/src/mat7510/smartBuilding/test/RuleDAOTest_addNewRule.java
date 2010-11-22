package mat7510.smartBuilding.test;

import java.util.Set;

import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.DeviceEvent;
import mat7510.smartBuilding.model.Rule;
import mat7510.smartBuilding.model.RuleDAO;
import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuildingDriverAC.DeviceDriverAC;

public class RuleDAOTest_addNewRule {

	public static void main(String[] args) throws SmartBuildingException {
		
		Rule newRule = new Rule.Builder("RULE-PRUEBA-TEST", "Rule dada de alta desde la clase test RuleDAOTest_addNewRule").build();
		DeviceDriver ac = new DeviceDriverAC("AC-PISO24-0358", "Aire Acond Bs As");
		newRule.setDeviceAction(ac.getDeviceActionByName("TURN ON AC"));
		
		newRule.addDeviceEvent(ac.getDeviceEventByName("FUNCTION AC WARM"));
		newRule.addDeviceEvent(ac.getDeviceEventByName("TURN ON AC"));
	
		Set<Rule> rules = RuleDAO.getInstance().getRules();
		rules.add(newRule);
		
		RuleDAO.getInstance().setRules(rules);
		

		rules = RuleDAO.getInstance().getRules();
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
