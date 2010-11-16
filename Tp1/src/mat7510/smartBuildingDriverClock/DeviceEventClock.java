package mat7510.smartBuildingDriverClock;

import mat7510.smartBuilding.DeviceEvent;

public abstract class DeviceEventClock implements DeviceEvent {

	private DeviceDriverClock clock;

	public DeviceEventClock(DeviceDriverClock clock) {
		if (clock == null) 
			throw new NullPointerException("Clock can´t be null");
		this.clock = clock;
	}

	public DeviceDriverClock getClock() {
		return clock;
	}

}