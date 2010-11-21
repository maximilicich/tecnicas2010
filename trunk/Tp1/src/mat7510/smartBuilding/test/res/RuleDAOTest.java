package mat7510.smartBuilding.test.res;

import java.util.Set;

import mat7510.smartBuilding.model.DeviceEvent;
import mat7510.smartBuilding.model.Rule;
import mat7510.smartBuilding.model.RuleDAO;
import mat7510.smartBuilding.model.SmartBuildingException;

public class RuleDAOTest {

	public static void main(String[] args) throws SmartBuildingException {
		
		Set<Rule> rules = RuleDAO.getInstance().getRules();
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
