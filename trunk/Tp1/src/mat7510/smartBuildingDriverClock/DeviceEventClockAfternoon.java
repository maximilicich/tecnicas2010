package mat7510.smartBuildingDriverClock;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventClockAfternoon extends DeviceEventClock {

	public DeviceEventClockAfternoon(DeviceDriverClock clock) {
		super(clock);
	}

	@Override
	public String getEventName() {
		return "Afternoon";
	}

	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventClockAfternoon))
			return false;
		return (((DeviceEventClockAfternoon)anotherEvent).getClock().getDeviceID().equals(this.getClock().getDeviceID()));
	}

}