package mat7510.smartBuildingDrivers;


public class DeviceActionACOn extends DeviceActionACOnOff {

	public DeviceActionACOn(DeviceDriverAC deviceDriverAC) {
		super(deviceDriverAC);
	}

	@Override
	public void changeOnOff() {
		this.getDeviceDriverAC().setMapEntry("EncendidoAC", "OFF");
		
	}


}
