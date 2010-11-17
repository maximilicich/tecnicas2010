package mat7510.smartBuildingDriverAC;


public class DeviceActionACOn extends DeviceActionACOnOff {

	static final String actionName = "TURN ON AC";
	static final String attr = "STATE AC"; 
	static final String value = "ON"; 
	
	public DeviceActionACOn(DeviceDriverAC deviceDriverAC,DeviceEventAC event) {
		super(deviceDriverAC, actionName, attr, value, event);
	}

	@Override
	public void changeOnOff() {
		this.getDeviceDriverAC().setMapEntry("EncendidoAC", "OFF");
		
	}


}
