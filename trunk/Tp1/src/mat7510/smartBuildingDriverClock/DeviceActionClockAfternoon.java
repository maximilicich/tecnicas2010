package mat7510.smartBuildingDriverClock;

public class DeviceActionClockAfternoon extends DeviceActionClock {
	
	static final String actionName = "CHANGE CLOCK TIME TO AFTERNOON";
	static final String attr = "DAY TIME"; 
	static final String value = "AFTERNOON"; 
	
	public DeviceActionClockAfternoon(DeviceDriverClock deviceDriverClock,DeviceEventClock event) {
		super(deviceDriverClock, actionName, attr, value, event);
	}

	@Override
	public void changeClockTime() {
		this.getDeviceDriverClock().setMapEntry(this.getAttr(), this.getValue());
	}
}
