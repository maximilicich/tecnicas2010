package mat7510.smartBuildingDriverClock;

public class DeviceActionClockEvening extends DeviceActionClock {

	static final String actionName = "CHANGE_CLOCK_EVENING";
	static final String attr = "DAY_TIME"; 
	static final String value = "EVENING"; 
	
	public DeviceActionClockEvening(DeviceDriverClock deviceDriverClock,DeviceEventClock event) {
		super(deviceDriverClock, actionName, attr, value, event);
	}

	@Override
	public void changeClockTime() {
		this.getDeviceDriverClock().setMapEntry(this.getAttr(), this.getValue());
	}

}
