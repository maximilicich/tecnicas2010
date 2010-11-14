package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsFuncNormal extends DeviceActionLightsFunc {


	public DeviceActionLightsFuncNormal(DeviceDriverLights deviceDriverLights) {
		super(deviceDriverLights);
	}

	@Override
	public void changeLightsFunc() {
		this.getDeviceDriverLights().setMapEntry("FuncLights", "Normal");
	}


}
