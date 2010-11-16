package mat7510.smartBuildingDriverClock;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventClockMoorning extends DeviceEventClock {

	public DeviceEventClockMoorning(DeviceDriverClock clock) {
		super(clock);
	}

	@Override
	public String getEventName() {
		return "Moorning";
	}

	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventClockMoorning))
			return false;
		return (((DeviceEventClockMoorning)anotherEvent).getClock().getDeviceID().equals(this.getClock().getDeviceID()));
	}

}