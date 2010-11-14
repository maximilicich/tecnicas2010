package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsOff extends DeviceActionLightsOnOff {

	public DeviceActionLightsOff(DeviceDriverLights deviceDriverLights) {
		super(deviceDriverLights);
	}

	@Override
	public void changeOnOff() {
		this.getDeviceDriverLights().setMapEntry("EncendidoLights", "OFF");			
	}

	
}
