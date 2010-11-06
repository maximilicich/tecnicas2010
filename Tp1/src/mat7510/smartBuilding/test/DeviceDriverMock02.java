package mat7510.smartBuilding.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mat7510.eventManagerApi.version2.EventListener;
import mat7510.smartBuilding.DeviceAction;
import mat7510.smartBuilding.DeviceDriver;
import mat7510.smartBuilding.DeviceEvent;

public class DeviceDriverMock02 implements DeviceDriver {

	@Override
	public void addEventListener(EventListener eventListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DeviceAction> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceEvent> getEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getState() {
		// TODO Auto-generated method stub
		Map<String, String> state = new HashMap<String, String>();
		state.put("Mock02 attr a", "Mock02 value a");
		state.put("Mock02 attr b", "Mock02 value b");
		state.put("Mock02 attr c", "Mock02 value c");
		return state;
	}

}
