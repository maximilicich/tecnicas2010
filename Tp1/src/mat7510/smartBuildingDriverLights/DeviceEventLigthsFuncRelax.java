package mat7510.smartBuildingDriverLights;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventLigthsFuncRelax extends DeviceEventLights {

		public DeviceEventLigthsFuncRelax(DeviceDriverLights lights) {
			super(lights);
		}

		@Override
		public String getEventName() {
			return "Function Lights Relax";
		}

		@Override
		public boolean equals(Event anotherEvent) {
			if (!(anotherEvent instanceof DeviceEventLigthsFuncRelax))
				return false;
			return (((DeviceEventLigthsFuncRelax)anotherEvent).getLights().getDeviceID().equals(this.getLights().getDeviceID()));
		}

	}
