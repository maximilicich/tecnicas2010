package mat7510.smartBuildingDriverAC;

import mat7510.eventManagerApi.version2.Event;

public class DeviceEventACOff extends DeviceEventAC {

		public DeviceEventACOff(DeviceDriverAC ac) {
			super(ac, "TURN OFF AC");
		}

		@Override
		public boolean equals(Event anotherEvent) {
			if (!(anotherEvent instanceof DeviceEventACOff))
				return false;
			return (((DeviceEventACOff)anotherEvent).getAc().getDeviceID().equals(this.getAc().getDeviceID()));
		}

}
