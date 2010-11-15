package mat7510.smartBuildingDriverLights;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mat7510.eventManagerApi.version2.EventListener;
import mat7510.smartBuilding.DeviceAction;
import mat7510.smartBuilding.DeviceDriver;
import mat7510.smartBuilding.DeviceEvent;

public class DeviceDriverLights extends DeviceDriver {

	private List<DeviceAction> deviceActions;
	private List<DeviceEvent>  deviceEvents;
	private Map<String,String> stateMap;


	public DeviceDriverLights(String deviceID, String deviceDescription) {

		super(deviceID, deviceDescription);

		stateMap = new LinkedHashMap<String, String>();
		stateMap.put("FuncLights", "Normal");
		stateMap.put("EncendidoLights", "ON");

		deviceActions = new ArrayList<DeviceAction>();
		deviceActions.add(new DeviceActionLightsFuncRelax(this));
		deviceActions.add(new DeviceActionLightsFuncParty(this));
		deviceActions.add(new DeviceActionLightsFuncRomantic(this));
		deviceActions.add(new DeviceActionLightsFuncNormal(this));
		deviceActions.add(new DeviceActionLightsOn(this));
		deviceActions.add(new DeviceActionLightsOff(this));

		deviceEvents = new ArrayList<DeviceEvent>();

	}

	@Override
	public List<DeviceAction> getActions() {
		return deviceActions;
	}

	@Override
	public List<DeviceEvent> getEvents() {
		return deviceEvents;
	}

	@Override
	public Map<String, String> getState() {
		return stateMap;
	}


	public void setAction( DeviceAction deviceAction ) {

		deviceActions.add(deviceAction);

	}

	public void setEvent( DeviceEvent deviceEvent ) {

		deviceEvents.add(deviceEvent);

	}

	public void setMapEntry ( String key, String value ) {

		stateMap.put(key, value);

	}

}
