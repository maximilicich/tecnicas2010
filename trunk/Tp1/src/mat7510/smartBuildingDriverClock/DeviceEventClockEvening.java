package mat7510.smartBuildingDriverClock;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventClockEvening extends DeviceEventClock {

	public DeviceEventClockEvening(DeviceDriverClock clock) {
		super(clock, "Evening");
	}


	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventClockEvening))
			return false;
		return (((DeviceEventClockEvening)anotherEvent).getClock().getDeviceID().equals(this.getClock().getDeviceID()));
	}

}