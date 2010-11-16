package mat7510.smartBuildingDriverLights;

import mat7510.eventManagerApi.version2.Event;

public class deviceEventLigthsFuncRomantic extends DeviceEventLights {

		public deviceEventLigthsFuncRomantic(DeviceDriverLights lights) {
			super(lights);
		}

		@Override
		public String getEventName() {
			return "Function Lights Romantic";
		}

		@Override
		public boolean equals(Event anotherEvent) {
			if (!(anotherEvent instanceof deviceEventLigthsFuncRomantic))
				return false;
			return (((deviceEventLigthsFuncRomantic)anotherEvent).getLights().getDeviceID().equals(this.getLights().getDeviceID()));
		}

	}