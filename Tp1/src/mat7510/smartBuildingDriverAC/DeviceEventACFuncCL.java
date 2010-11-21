package mat7510.smartBuildingDriverAC;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventACFuncCL extends DeviceEventAC {


	public DeviceEventACFuncCL(DeviceDriverAC ac) {
		super(ac, "FUNCTION AC WARM");
	}


	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventACFuncCL))
			return false;
		return (((DeviceEventACFuncCL)anotherEvent).getAc().getDeviceID().equals(this.getAc().getDeviceID()));
	}

}
