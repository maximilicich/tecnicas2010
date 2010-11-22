package mat7510.smartBuildingDriverClock;

public class DeviceActionClockEvening extends DeviceActionClock {

	static final String actionName = "CHANGE CLOCK TIME TO EVENING";
	static final String attr = "DAY TIME"; 
	static final String value = "EVENING"; 
	
	public DeviceActionClockEvening(DeviceDriverClock deviceDriverClock,DeviceEventClock event) {
		super(deviceDriverClock, actionName, attr, value, event);
	}

	@Override
	public void changeClockTime() {
		this.getDeviceDriverClock().setMapEntry(this.getAttr(), this.getValue());
	}

}
