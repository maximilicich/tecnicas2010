package mat7510.smartBuildingDriverLights;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventLigthsFuncParty extends DeviceEventLights {

		public DeviceEventLigthsFuncParty(DeviceDriverLights lights) {
			super(lights);
		}

		@Override
		public String getEventName() {
			return "Function Lights Party";
		}

		@Override
		public boolean equals(Event anotherEvent) {
			if (!(anotherEvent instanceof DeviceEventLigthsFuncParty))
				return false;
			return (((DeviceEventLigthsFuncParty)anotherEvent).getLights().getDeviceID().equals(this.getLights().getDeviceID()));
		}

	}