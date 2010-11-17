package mat7510.smartBuildingDriverAC;

public class DeviceActionACFuncCL extends DeviceActionACFunc {

	static final String actionName = "FUNCTION AC WARM";
	static final String attr = "FUNCTION"; 
	static final String value = "CL"; 
	
	public DeviceActionACFuncCL(DeviceDriverAC deviceDriverAC,DeviceEventAC event) {
		super(deviceDriverAC, actionName, attr, value, event);
	}

	@Override
	public void changeACFunc( ) {
		
		getDeviceDriverAC().setMapEntry(this.getAttr(),this.getValue());		
	}

}
