package mat7510.smartBuildingDriverClock;


public class DeviceActionClockMoorning extends DeviceActionClock {


	static final String actionName = "CHANGE_CLOCK_MOORNING";
	static final String attr = "DAY_TIME"; 
	static final String value = "MOORNING"; 
	
	
	public DeviceActionClockMoorning(DeviceDriverClock deviceDriverClock,DeviceEventClock event) {
		super(deviceDriverClock, actionName, attr, value, event);
	}

	@Override
	public void changeClockTime() {
		this.getDeviceDriverClock().setMapEntry(this.getAttr(), this.getValue());
	}


}
