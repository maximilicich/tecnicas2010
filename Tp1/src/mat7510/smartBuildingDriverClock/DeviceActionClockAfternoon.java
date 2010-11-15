package mat7510.smartBuildingDriverClock;

public class DeviceActionClockAfternoon extends DeviceActionClock {

	public DeviceActionClockAfternoon(DeviceDriverClock deviceDriverClock) {
		super(deviceDriverClock);
	}

	@Override
	public void changeClockTime() {
		this.getDeviceDriverClock().setMapEntry("DayTime", "Afternoon");
	}
}
