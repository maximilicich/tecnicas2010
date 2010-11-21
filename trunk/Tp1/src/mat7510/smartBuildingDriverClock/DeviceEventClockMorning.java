package mat7510.smartBuildingDriverClock;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventClockMorning extends DeviceEventClock {

	public DeviceEventClockMorning(DeviceDriverClock clock) {
		super(clock, "Morning");
	}


	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventClockMorning))
			return false;
		return (((DeviceEventClockMorning)anotherEvent).getClock().getDeviceID().equals(this.getClock().getDeviceID()));
	}

}