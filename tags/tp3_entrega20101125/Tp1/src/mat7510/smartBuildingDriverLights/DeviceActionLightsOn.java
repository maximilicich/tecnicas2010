package mat7510.smartBuildingDriverLights;


public class DeviceActionLightsOn extends DeviceActionLightsOnOff {

	static final String actionName = "Lights On";
	static final String attr = "LIGHTS STATE ON/OFF"; 
	static final String value = "ON"; 
	
	public DeviceActionLightsOn(DeviceDriverLights deviceDriverLights,DeviceEventLights event) {
		super(deviceDriverLights, actionName, attr, value, event);
	}

	@Override
	public void changeOnOff() {
		this.getDeviceDriverLights().setMapEntry(this.getAttr(),this.getValue());	
		
	}


}
