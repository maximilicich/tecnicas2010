package mat7510.smartBuildingDriverLights;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventLigthsFuncNormal extends DeviceEventLights {

	public DeviceEventLigthsFuncNormal(DeviceDriverLights lights) {
		super(lights);
	}

	@Override
	public String getEventName() {
		return "Function Lights Normal";
	}

	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventLigthsFuncNormal))
			return false;
		return (((DeviceEventLigthsFuncNormal)anotherEvent).getLights().getDeviceID().equals(this.getLights().getDeviceID()));
	}

}