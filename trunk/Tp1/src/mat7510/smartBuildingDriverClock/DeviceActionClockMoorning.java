package mat7510.smartBuildingDriverClock;


public class DeviceActionClockMoorning extends DeviceActionClock {

	public DeviceActionClockMoorning(DeviceDriverClock deviceDriverClock) {
		super(deviceDriverClock);
	}

	@Override
	public void changeClockTime() {
		this.getDeviceDriverClock().setMapEntry("DayTime", "Moorning");
	}


}
