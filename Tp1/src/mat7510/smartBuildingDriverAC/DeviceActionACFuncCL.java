package mat7510.smartBuildingDriverAC;



public class DeviceActionACFuncCL extends DeviceActionACFunc {

	public DeviceActionACFuncCL(DeviceDriverAC deviceDriverAC) {
		super(deviceDriverAC);
	}

	@Override
	public void changeACFunc( ) {
		
		getDeviceDriverAC().setMapEntry("FuncAC", "CL");		
	}

}
