package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsOn extends DeviceActionLightsOnOff {

	public DeviceActionLightsOn(DeviceDriverLights deviceDriverLights) {
		super(deviceDriverLights);
	}

	@Override
	public void changeOnOff() {
		this.getDeviceDriverLights().setMapEntry("EncendidoLights", "ON");	
		
	}


}
