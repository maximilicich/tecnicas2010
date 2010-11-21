package mat7510.smartBuildingDriverLights;

import mat7510.smartBuilding.model.DeviceEvent;

	public abstract class DeviceEventLights extends DeviceEvent {

		private DeviceDriverLights lights;

		public DeviceEventLights(DeviceDriverLights lights, String eventName) {
			super(lights, eventName);
			this.lights = lights;
		}

		public DeviceDriverLights getLights() {
			return lights;
		}

	}