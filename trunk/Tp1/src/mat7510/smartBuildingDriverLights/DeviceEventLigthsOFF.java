package mat7510.smartBuildingDriverLights;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventLigthsOFF extends DeviceEventLights {

	public DeviceEventLigthsOFF(DeviceDriverLights lights) {
		super(lights, "Lights Off");
	}


	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof DeviceEventLigthsOFF))
			return false;
		return (((DeviceEventLigthsOFF)anotherEvent).getLights().getDeviceID().equals(this.getLights().getDeviceID()));
	}

}