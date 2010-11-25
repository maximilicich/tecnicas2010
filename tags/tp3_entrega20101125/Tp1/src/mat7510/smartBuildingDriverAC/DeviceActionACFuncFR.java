package mat7510.smartBuildingDriverAC;

public class DeviceActionACFuncFR extends DeviceActionACFunc {

	static final String actionName = "FUNCTION AC COLD";
	static final String attr = "FUNCTION"; 
	static final String value = "FR"; 
	
	public DeviceActionACFuncFR(DeviceDriverAC deviceDriverAC,DeviceEventAC event) {
		super(deviceDriverAC, actionName, attr, value, event);
	}

	@Override
	public void changeACFunc( ) {

		getDeviceDriverAC().setMapEntry(this.getAttr(),this.getValue());		
	}

}
