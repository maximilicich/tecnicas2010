package mat7510.smartBuildingDriverAC;

public class DeviceActionACFuncCL extends DeviceActionACFunc {

	public DeviceActionACFuncCL(DeviceDriverAC deviceDriverAC,
								String actionName,
								String attr,
								String value,
								DeviceEventAC event) {
		
		super(deviceDriverAC, actionName, attr, value, event);
	}

	@Override
	public void changeACFunc( ) {
		
		getDeviceDriverAC().setMapEntry(this.getAttr(),this.getValue());		
	}

}
