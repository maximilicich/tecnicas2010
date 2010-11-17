package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsFuncNormal extends DeviceActionLightsFunc {
	
	static final String actionName = "Function Lights Normal";
	static final String attr = "FUNCTION_LIGHTS"; 
	static final String value = "NORMAL"; 
	

	public DeviceActionLightsFuncNormal(DeviceDriverLights deviceDriverLights,DeviceEventLights event) {
		super(deviceDriverLights, actionName, attr, value, event);
	}

	@Override
	public void changeLightsFunc() {
		this.getDeviceDriverLights().setMapEntry(this.getAttr(), this.getValue());
	}


}
