package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsFuncRelax extends DeviceActionLightsFunc {
	
	static final String actionName = "Function Lights Relax";
	static final String attr = "FUNCTION_LIGHTS"; 
	static final String value = "RELAX"; 

	public DeviceActionLightsFuncRelax(DeviceDriverLights deviceDriverLights,DeviceEventLights event) {
		super(deviceDriverLights, actionName, attr, value, event);
	}

	@Override
	public void changeLightsFunc() {
		this.getDeviceDriverLights().setMapEntry(this.getAttr(), this.getValue());		
	}


}
