package mat7510.smartBuildingDriverLights;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mat7510.smartBuilding.model.DeviceAction;
import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.DeviceEvent;

public class DeviceDriverLights extends DeviceDriver {

	private List<DeviceAction> deviceActions = new ArrayList<DeviceAction>();
	private List<DeviceEvent>  deviceEvents  = new ArrayList<DeviceEvent>();
	private Map<String,String> stateMap = new LinkedHashMap<String, String>();

	static final String ATTR_STATE_FUNCTION = "FUNCTION_LIGHTS";
	static final String ATTR_STATE_LIGHTS = "LIGHTS_STATE_ON/OFF";
	
	static final String ATTR_VALUE_ON = "ON";
	static final String ATTR_VALUE_OFF = "OFF";
	
	static final String ATTR_VALUE_NORMAL = "NORMAL";
	static final String ATTR_VALUE_RELAX = "RELAX";
	static final String ATTR_VALUE_PARTY = "PARTY";
	static final String ATTR_VALUE_ROMANTIC = "ROMANTIC";
	
	public DeviceDriverLights(String deviceID, String deviceDescription) {

		super(deviceID, deviceDescription);

		stateMap.put(ATTR_STATE_FUNCTION, ATTR_VALUE_NORMAL);
		stateMap.put(ATTR_STATE_LIGHTS, ATTR_VALUE_ON );

		
		DeviceEventLights deviceEventLigthsON 			= new DeviceEventLigthsON(this);
		DeviceEventLights deviceEventLigthsOFF			= new DeviceEventLigthsOFF(this);
		DeviceEventLights deviceEventLigthsFuncNormal 	= new DeviceEventLigthsFuncNormal(this);
		DeviceEventLights deviceEventLigthsFuncRelax 	= new DeviceEventLigthsFuncRelax(this);
		DeviceEventLights deviceEventLigthsFuncParty 	= new DeviceEventLigthsFuncParty(this);
		DeviceEventLights deviceEventLigthsFuncRomantic = new deviceEventLigthsFuncRomantic(this);
		
		this.deviceEvents.add(deviceEventLigthsON);
		this.deviceEvents.add(deviceEventLigthsOFF);
		this.deviceEvents.add(deviceEventLigthsFuncNormal);
		this.deviceEvents.add(deviceEventLigthsFuncRelax);
		this.deviceEvents.add(deviceEventLigthsFuncParty);
		this.deviceEvents.add(deviceEventLigthsFuncRomantic);
		
		this.deviceActions.add(new DeviceActionLightsOn(this,deviceEventLigthsON));
		this.deviceActions.add(new DeviceActionLightsOff(this,deviceEventLigthsOFF));
		this.deviceActions.add(new DeviceActionLightsFuncNormal(this,deviceEventLigthsFuncNormal));
		this.deviceActions.add(new DeviceActionLightsFuncRelax(this,deviceEventLigthsFuncRelax));
		this.deviceActions.add(new DeviceActionLightsFuncParty(this,deviceEventLigthsFuncParty));
		this.deviceActions.add(new DeviceActionLightsFuncRomantic(this,deviceEventLigthsFuncRomantic));

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
