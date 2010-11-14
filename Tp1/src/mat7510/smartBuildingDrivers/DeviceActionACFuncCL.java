package mat7510.smartBuildingDrivers;



public class DeviceActionACFuncCL extends DeviceActionACFunc {

	public DeviceActionACFuncCL(DeviceDriverAC deviceDriverAC) {
		super(deviceDriverAC);
	}

	@Override
	public void changeACFunc( ) {
		
		getDeviceDriverAC().setMapEntry("FuncAC", "CL");		
	}

}
