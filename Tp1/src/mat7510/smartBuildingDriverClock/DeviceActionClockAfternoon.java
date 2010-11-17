package mat7510.smartBuildingDriverClock;

public class DeviceActionClockAfternoon extends DeviceActionClock {
	
	static final String actionName = "CHANGE_CLOCK_AFTERNOON";
	static final String attr = "DAY_TIME"; 
	static final String value = "AFTERNOON"; 
	
	public DeviceActionClockAfternoon(DeviceDriverClock deviceDriverClock,DeviceEventClock event) {
		super(deviceDriverClock, actionName, attr, value, event);
	}

	@Override
	public void changeClockTime() {
		this.getDeviceDriverClock().setMapEntry(this.getAttr(), this.getValue());
	}
}
