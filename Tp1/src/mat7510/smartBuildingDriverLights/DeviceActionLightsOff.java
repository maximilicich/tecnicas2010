package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsOff extends DeviceActionLightsOnOff {

	static final String actionName = "Lights Off";
	static final String attr = "LIGHTS_STATE_ON/OFF"; 
	static final String value = "OFF"; 

	public DeviceActionLightsOff(DeviceDriverLights deviceDriverLights,	DeviceEventLights event) {
		super(deviceDriverLights, actionName, attr, value, event);
	}

	@Override
	public void changeOnOff() {
		this.getDeviceDriverLights().setMapEntry(this.getAttr(),this.getValue());		
	}

	
}
