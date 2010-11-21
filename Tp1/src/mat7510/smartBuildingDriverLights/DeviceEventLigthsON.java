package mat7510.smartBuildingDriverLights;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventLigthsON extends DeviceEventLights {

	public DeviceEventLigthsON(DeviceDriverLights lights) {
		super(lights, "Lights On");
	}


	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventLigthsON))
			return false;
		return (((DeviceEventLigthsON)anotherEvent).getLights().getDeviceID().equals(this.getLights().getDeviceID()));
	}

}