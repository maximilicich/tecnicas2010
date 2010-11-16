package mat7510.smartBuildingDriverAC;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventTempACUp extends DeviceEventAC {

	public DeviceEventTempACUp(DeviceDriverAC ac) {
		super(ac);
	}

	@Override
	public String getEventName() {
		return "TEMP UP AC";
	}

	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventTempACUp))
			return false;
		return (((DeviceEventTempACUp)anotherEvent).getAc().getDeviceID().equals(this.getAc().getDeviceID()));
	}

}
