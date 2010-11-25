package mat7510.smartBuildingDriverClock;


public class DeviceActionClockMorning extends DeviceActionClock {


	static final String actionName = "CHANGE CLOCK TIME TO MOORNING";
	static final String attr = "DAY TIME"; 
	static final String value = "MOORNING"; 
	
	
	public DeviceActionClockMorning(DeviceDriverClock deviceDriverClock,DeviceEventClock event) {
		super(deviceDriverClock, actionName, attr, value, event);
	}

	@Override
	public void changeClockTime() {
		this.getDeviceDriverClock().setMapEntry(this.getAttr(), this.getValue());
	}


}
