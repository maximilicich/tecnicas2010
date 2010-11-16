package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsFuncParty extends DeviceActionLightsFunc {

	public DeviceActionLightsFuncParty(DeviceDriverLights deviceDriverLights,
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
