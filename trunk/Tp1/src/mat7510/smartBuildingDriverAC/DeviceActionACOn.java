package mat7510.smartBuildingDriverAC;


public class DeviceActionACOn extends DeviceActionACOnOff {

	
	public DeviceActionACOn(DeviceDriverAC deviceDriverAC,
							String actionName,
							String attr, 
							String value,
							DeviceEventAC event) {
		
		super(deviceDriverAC, actionName, attr, value, event);
	}

	@Override
	public void changeOnOff() {
		this.getDeviceDriverAC().setMapEntry("EncendidoAC", "OFF");
		
	}


}
