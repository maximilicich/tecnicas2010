package mat7510.smartBuildingDriverClock;

import mat7510.smartBuilding.DeviceAction;

public abstract class DeviceActionClock implements DeviceAction{

	
	private DeviceDriverClock deviceDriverClock;
	private String actionName;
	
	public DeviceActionClock ( DeviceDriverClock deviceDriverClock){
		
		this.setDeviceDriverClock(deviceDriverClock);
		actionName = "DayTime";
		
	}
	
	@Override
	public void execute() {	
		changeClockTime();	
	}

	@Override
	public String getActionName() {
		return actionName;
	}
	
	public abstract void changeClockTime ( );
	
	public void setDeviceDriverClock(DeviceDriverClock deviceDriverClock) {
		this.deviceDriverClock = deviceDriverClock;
	}

	public DeviceDriverClock getDeviceDriverClock() {
		return deviceDriverClock;
	}


}