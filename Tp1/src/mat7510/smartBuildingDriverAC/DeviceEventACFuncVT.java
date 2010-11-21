package mat7510.smartBuildingDriverAC;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventACFuncVT extends DeviceEventAC {

	public DeviceEventACFuncVT(DeviceDriverAC ac) {
		super(ac, "FUNCTION AC VENT");
	}

	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventACFuncVT))
			return false;
		return (((DeviceEventACFuncVT)anotherEvent).getAc().getDeviceID().equals(this.getAc().getDeviceID()));
	}

}
