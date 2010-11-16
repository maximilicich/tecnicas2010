package mat7510.smartBuildingDriverAC;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventACFuncFR extends DeviceEventAC {

	public DeviceEventACFuncFR(DeviceDriverAC ac) {
		super(ac);
	}

	@Override
	public String getEventName() {
		return "FUNCTION AC COLD";
	}

	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventACFuncFR))
			return false;
		return (((DeviceEventACFuncFR)anotherEvent).getAc().getDeviceID().equals(this.getAc().getDeviceID()));
	}

}
