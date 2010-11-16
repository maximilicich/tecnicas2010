package mat7510.smartBuildingDriverAC;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventTempACDown extends DeviceEventAC {

		public DeviceEventTempACDown(DeviceDriverAC ac) {
			super(ac);
		}

		@Override
		public String getEventName() {
			return "TEMP DOWN AC";
		}

		@Override
		public boolean equals(Event anotherEvent) {
			if (!(anotherEvent instanceof DeviceEventTempACDown))
				return false;
			return (((DeviceEventTempACDown)anotherEvent).getAc().getDeviceID().equals(this.getAc().getDeviceID()));
		}

}
