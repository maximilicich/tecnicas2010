package mat7510.smartBuildingDriverLights;

public class DeviceActionLightsFuncRomantic extends DeviceActionLightsFunc {

	
	static final String actionName = "Function Lights Romantic";
	static final String attr = "FUNCTION LIGHTS"; 
	static final String value = "ROMANTIC"; 

	public DeviceActionLightsFuncRomantic(	DeviceDriverLights deviceDriverLights, DeviceEventLights event) {
		super(deviceDriverLights, actionName, attr, value, event);
	}

	@Override
	public void changeLightsFunc() {
		this.getDeviceDriverLights().setMapEntry(this.getAttr(), this.getValue());	
	}


}
