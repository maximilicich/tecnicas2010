package mat7510.smartBuildingDriverAC;

import mat7510.smartBuilding.model.DeviceEvent;

public abstract class DeviceEventAC implements DeviceEvent {

		private DeviceDriverAC ac;

		public DeviceEventAC(DeviceDriverAC ac) {
			if (ac == null) 
				throw new NullPointerException("AC can´t be null");
			this.ac = ac;
		}

		public DeviceDriverAC getAc() {
			return ac;
		}

	}