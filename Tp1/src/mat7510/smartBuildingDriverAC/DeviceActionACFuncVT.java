package mat7510.smartBuildingDriverAC;


public class DeviceActionACFuncVT extends DeviceActionACFunc {

	public DeviceActionACFuncVT(DeviceDriverAC deviceDriverAC,
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
