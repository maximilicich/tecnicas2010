package mat7510.smartBuilding.test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mat7510.smartBuilding.DeviceAction;
import mat7510.smartBuilding.DeviceDriver;
import mat7510.smartBuilding.DeviceEvent;

public class DeviceDriverMock02 extends DeviceDriver {

	public DeviceDriverMock02(String deviceID, String deviceDescription) {
		super(deviceID, deviceDescription);
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
		Map<String, String> state = new LinkedHashMap<String, String>();
		state.put("Mock02 attr a", "Mock02 value a");
		state.put("Mock02 attr b", "Mock02 value b");
		state.put("Mock02 attr c", "Mock02 value c");
		return state;
	}

}
