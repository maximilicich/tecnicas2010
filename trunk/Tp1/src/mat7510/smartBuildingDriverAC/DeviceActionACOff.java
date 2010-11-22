package mat7510.smartBuildingDriverAC;


public class DeviceActionACOff extends DeviceActionACOnOff {
	
	static final String actionName = "TURN OFF AC";
	static final String attr = "STATE AC"; 
	static final String value = "OFF"; 

	public DeviceActionACOff(DeviceDriverAC deviceDriverAC, DeviceEventAC event) {
		super(deviceDriverAC, actionName, attr, value, event);
	}

	@Override
	public void changeOnOff() {
		this.getDeviceDriverAC().setMapEntry(attr,value);
	}


}
