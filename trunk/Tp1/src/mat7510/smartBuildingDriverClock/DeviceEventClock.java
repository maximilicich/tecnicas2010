package mat7510.smartBuildingDriverClock;

import mat7510.smartBuilding.domain.devicedriver.DeviceEvent;

public abstract class DeviceEventClock extends DeviceEvent {

	private DeviceDriverClock clock;

	public DeviceEventClock(DeviceDriverClock clock, String eventName) {
		super(clock, eventName);
		this.clock = clock;
	}

	public DeviceDriverClock getClock() {
		return clock;
	}

}