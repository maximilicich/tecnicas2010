package mat7510.smartBuildingDriverAC;


public class DeviceActionACOff extends DeviceActionACOnOff {

	public DeviceActionACOff(DeviceDriverAC deviceDriverAC) {
		super(deviceDriverAC);
	}

	@Override
	public void changeOnOff() {
		this.getDeviceDriverAC().setMapEntry("EncendidoAC", "ON");
	}


}
