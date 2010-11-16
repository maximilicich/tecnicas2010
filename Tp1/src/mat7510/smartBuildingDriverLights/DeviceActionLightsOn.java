package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsOn extends DeviceActionLightsOnOff {

	public DeviceActionLightsOn(DeviceDriverLights deviceDriverLights,
								String actionName,
								String attr, 
								String value,
								DeviceEventLights event) {
		
		super(deviceDriverLights, actionName, attr, value, event);
	}

	@Override
	public void changeOnOff() {
		this.getDeviceDriverLights().setMapEntry(this.getAttr(),this.getValue());	
		
	}


}
