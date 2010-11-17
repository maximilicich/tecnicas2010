package mat7510.smartBuildingDriverLights;

import mat7510.smartBuilding.model.DeviceEvent;

	public abstract class DeviceEventLights implements DeviceEvent {

		private DeviceDriverLights lights;

		public DeviceEventLights(DeviceDriverLights lights) {
			if (lights == null) 
				throw new NullPointerException("Lights can´t be null");
			this.lights = lights;
		}

		public DeviceDriverLights getLights() {
			return lights;
		}

	}