package mat7510.smartBuildingDrivers;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mat7510.smartBuilding.DeviceAction;

public abstract class DeviceActionACFunc implements DeviceAction{

	
	private DeviceDriverAC deviceDriverAC;
	private String actionName;
	
	public DeviceActionACFunc ( DeviceDriverAC deviceDriverAC){
		
		this.setDeviceDriverAC(deviceDriverAC);
		actionName = "FuncAc";
		
	}
	
	@Override
	public void execute() {
		
		Map<String,String> stateMap = getDeviceDriverAC().getState();
		
		Iterator<Entry<String, String>> itStateMap = stateMap.entrySet().iterator();
		
		while (itStateMap.hasNext()) {
			Map.Entry<String, String> elem = (Map.Entry<String, String>)itStateMap.next();
			if ( this.getActionName() == elem.getKey()){
				changeACFunc();
			}
		}
	
	}

	@Override
	public String getActionName() {
		return actionName;
	}
	
	public abstract void changeACFunc ( );
	
	public void setDeviceDriverAC(DeviceDriverAC deviceDriverAC) {
		this.deviceDriverAC = deviceDriverAC;
	}

	public DeviceDriverAC getDeviceDriverAC() {
		return deviceDriverAC;
	}


}
