package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsFuncNormal extends DeviceActionLightsFunc {

	public DeviceActionLightsFuncNormal(DeviceDriverLights deviceDriverLights,
										String actionName,
										String attr,
										String value,
										DeviceEventLights event) {
		
		super(deviceDriverLights, actionName, attr, value, event);
	}

	@Override
	public void changeLightsFunc() {
		this.getDeviceDriverLights().setMapEntry(this.getAttr(), this.getValue());
	}


}
