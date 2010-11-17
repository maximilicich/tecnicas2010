package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsFuncParty extends DeviceActionLightsFunc {
	
	static final String actionName = "Function Lights Party";
	static final String attr = "FUNCTION_LIGHTS"; 
	static final String value = "PARTY"; 

	public DeviceActionLightsFuncParty(DeviceDriverLights deviceDriverLights,DeviceEventLights event) {
		super(deviceDriverLights, actionName, attr, value, event);
	}

	@Override
	public void changeLightsFunc() {
		this.getDeviceDriverLights().setMapEntry(this.getAttr(), this.getValue());		
	}


}
