package mat7510.smartBuildingDriverAC;

import mat7510.smartBuilding.model.devicedriver.DeviceEvent;

public abstract class DeviceEventAC extends DeviceEvent {

		private DeviceDriverAC ac;

		public DeviceEventAC(DeviceDriverAC ac, String eventName) {
			super(ac, eventName);
			this.ac = ac;
		}

		public DeviceDriverAC getAc() {
			return ac;
		}

	}