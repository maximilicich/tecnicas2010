package mat7510.smartBuildingDriverClock;

public class DeviceActionClockEvening extends DeviceActionClock {

	public DeviceActionClockEvening(DeviceDriverClock deviceDriverClock) {
		super(deviceDriverClock);
	}

	@Override
	public void changeClockTime() {
		this.getDeviceDriverClock().setMapEntry("DayTime", "Evening");
	}

}
