package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsFuncRelax extends DeviceActionLightsFunc {

	public DeviceActionLightsFuncRelax(DeviceDriverLights deviceDriverLights) {
		super(deviceDriverLights);
	}

	@Override
	public void changeLightsFunc() {
		this.getDeviceDriverLights().setMapEntry("FuncLights", "Relax");	
	}


}
