package mat7510.smartBuildingDrivers;


public class DeviceActionACFuncFR extends DeviceActionACFunc {

	public DeviceActionACFuncFR(DeviceDriverAC deviceDriverAC) {
		super(deviceDriverAC);
	}


	@Override
	public void changeACFunc( ) {
	
		getDeviceDriverAC().setMapEntry("FuncAC", "FR");	
	}

}
