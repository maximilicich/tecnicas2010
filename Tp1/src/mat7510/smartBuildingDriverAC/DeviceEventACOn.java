package mat7510.smartBuildingDriverAC;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventACOn extends DeviceEventAC {

		public DeviceEventACOn(DeviceDriverAC ac) {
			super(ac);
		}

		@Override
		public String getEventName() {
			return "TURN ON AC";
		}

		@Override
		public boolean equals(Event anotherEvent) {
			if (!(anotherEvent instanceof DeviceEventACOn))
				return false;
			return (((DeviceEventACOn)anotherEvent).getAc().getDeviceID().equals(this.getAc().getDeviceID()));
		}

}
