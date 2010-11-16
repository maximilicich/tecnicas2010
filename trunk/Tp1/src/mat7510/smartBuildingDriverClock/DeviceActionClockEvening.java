package mat7510.smartBuildingDriverClock;

public class DeviceActionClockEvening extends DeviceActionClock {



	public DeviceActionClockEvening(DeviceDriverClock deviceDriverClock,
									String actionName,
									String attr,
									String value, 
									DeviceEventClock event) {
		super(deviceDriverClock, actionName, attr, value, event);
	}

	@Override
	public void changeClockTime() {
		this.getDeviceDriverClock().setMapEntry(this.getAttr(), this.getValue());
	}

}
