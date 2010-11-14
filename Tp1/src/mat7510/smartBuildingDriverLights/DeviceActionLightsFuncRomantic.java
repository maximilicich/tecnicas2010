package mat7510.smartBuildingDriverLights;

public class DeviceActionLightsFuncRomantic extends DeviceActionLightsFunc {

	public DeviceActionLightsFuncRomantic(DeviceDriverLights deviceDriverLights) {
		super(deviceDriverLights);
	}

	@Override
	public void changeLightsFunc() {
		this.getDeviceDriverLights().setMapEntry("FuncLights", "Romantic");
	}


}
