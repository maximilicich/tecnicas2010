package mat7510.smartBuildingDriverAC;


public class DeviceActionACFuncVT extends DeviceActionACFunc {

	
	static final String actionName = "FUNCTION AC VENT";
	static final String attr = "FUNCTION"; 
	static final String value = "VT"; 
	
	public DeviceActionACFuncVT(DeviceDriverAC deviceDriverAC,DeviceEventAC event) {
		super(deviceDriverAC, actionName, attr, value, event);
	}

	@Override
	public void changeACFunc( ) {

		getDeviceDriverAC().setMapEntry(this.getAttr(),this.getValue());		
	}

}
