package mat7510.smartBuilding.test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mat7510.smartBuilding.model.devicedriver.DeviceAction;
import mat7510.smartBuilding.model.devicedriver.DeviceDriver;
import mat7510.smartBuilding.model.devicedriver.DeviceEvent;

public class DeviceDriverMock01 extends DeviceDriver {

	public DeviceDriverMock01(String deviceID, String deviceDescription) {
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
		state.put("Mock01 attr a", "Mock01 value a");
		state.put("Mock01 attr b", "Mock01 value b");
		state.put("Mock01 attr c", "Mock01 value c");
		return state;
	}

	@Override
	public DeviceAction getDeviceActionByName(String deviceActionName) {
		return null;
	}
	
	@Override
	public DeviceEvent getDeviceEventByName(String deviceEventName) {
		return null;
	}


}
