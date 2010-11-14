package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsFuncParty extends DeviceActionLightsFunc {

	public DeviceActionLightsFuncParty(DeviceDriverLights deviceDriverLights) {
		super(deviceDriverLights);
	}

	@Override
	public void changeLightsFunc() {
		this.getDeviceDriverLights().setMapEntry("FuncLights", "Party");		
	}


}
