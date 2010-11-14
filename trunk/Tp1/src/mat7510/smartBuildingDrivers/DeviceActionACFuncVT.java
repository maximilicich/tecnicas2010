package mat7510.smartBuildingDrivers;



public class DeviceActionACFuncVT extends DeviceActionACFunc {

	public DeviceActionACFuncVT(DeviceDriverAC deviceDriverAC) {
		super(deviceDriverAC);
	}

	@Override
	public void changeACFunc( ) {
		getDeviceDriverAC().setMapEntry("FuncAC", "VT");	
		
	}
}
