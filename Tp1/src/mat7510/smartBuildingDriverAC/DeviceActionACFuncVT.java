package mat7510.smartBuildingDriverAC;



public class DeviceActionACFuncVT extends DeviceActionACFunc {

	public DeviceActionACFuncVT(DeviceDriverAC deviceDriverAC) {
		super(deviceDriverAC);
	}

	@Override
	public void changeACFunc( ) {
		getDeviceDriverAC().setMapEntry("FuncAC", "VT");	
		
	}
}
