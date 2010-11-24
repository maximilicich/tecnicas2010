package mat7510.smartBuildingDriverAC;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mat7510.smartBuilding.model.devicedriver.DeviceAction;
import mat7510.smartBuilding.model.devicedriver.DeviceDriver;
import mat7510.smartBuilding.model.devicedriver.DeviceEvent;

public class DeviceDriverAC extends DeviceDriver {
	
	private List<DeviceAction> deviceActions = new ArrayList<DeviceAction>();
	private List<DeviceEvent>  deviceEvents = new ArrayList<DeviceEvent>();
	private Map<String,String> stateMap = new LinkedHashMap<String, String>();
	
	static final String TEMP_MAX = "28";
	static final String TEMP_MIN = "17";
	
	static final String ATTR_FUNC_AC = "FUNCTION";
	static final String ATTR_TEMP_AC = "TEMP";
	static final String ATTR_STATE_AC = "STATE AC";
	
	public DeviceDriverAC(String deviceID, String deviceDescription) {
		
		super(deviceID, deviceDescription);
		
		stateMap.put(ATTR_FUNC_AC , "FR" );
		stateMap.put(ATTR_STATE_AC , "OFF");
		stateMap.put(ATTR_TEMP_AC, "20" );
		
		DeviceEventAC deviceEventTempACUp   = new DeviceEventTempACUp(this);
		DeviceEventAC deviceEventTempACDown = new DeviceEventTempACDown(this);
		DeviceEventAC deviceEventACFuncFR   = new DeviceEventACFuncFR(this);
		DeviceEventAC deviceEventACFuncCL   = new DeviceEventACFuncCL(this);
		DeviceEventAC deviceEventACFuncVT   = new DeviceEventACFuncVT(this);
		DeviceEventAC deviceEventACOn 		= new DeviceEventACOn(this);
		DeviceEventAC deviceEventACOff	    = new DeviceEventACOff(this);
		
		this.deviceEvents.add(deviceEventTempACUp);
		this.deviceEvents.add(deviceEventTempACDown);
		this.deviceEvents.add(deviceEventACFuncFR);
		this.deviceEvents.add(deviceEventACFuncCL);
		this.deviceEvents.add(deviceEventACFuncVT);
		this.deviceEvents.add(deviceEventACOn);
		this.deviceEvents.add(deviceEventACOff);
		
		deviceActions.add(new DeviceActionACTempUp(this,deviceEventTempACUp));
		deviceActions.add(new DeviceActionACTempDown(this,deviceEventTempACDown));
		deviceActions.add(new DeviceActionACFuncFR(this,deviceEventACFuncFR));
		deviceActions.add(new DeviceActionACFuncCL(this,deviceEventACFuncCL));
		deviceActions.add(new DeviceActionACFuncVT(this,deviceEventACFuncVT));
		deviceActions.add(new DeviceActionACOn(this,deviceEventACOn));
		deviceActions.add(new DeviceActionACOff(this,deviceEventACOff));
		
	}

	@Override
	public List<DeviceAction> getActions() {
		return deviceActions;
	}

	@Override
	public List<DeviceEvent> getEvents() {
		return deviceEvents;
	}

	@Override
	public Map<String, String> getState() {
		return stateMap;
	}

	
	public void setAction( DeviceAction deviceAction ) {
		
		deviceActions.add(deviceAction);
		
	}
	
	public void setEvent( DeviceEvent deviceEvent ) {
		
		deviceEvents.add(deviceEvent);
		
	}
	
	public void setMapEntry ( String key, String value ) {
		
		stateMap.put(key, value);
		
	}
	
	
	public String getTempMax(){
		return TEMP_MAX;
	}
	
	public String getTempMin(){
		return TEMP_MIN;
	}
	
	@Override
	public DeviceAction getDeviceActionByName(String deviceActionName) {
		for (DeviceAction deviceAction : deviceActions) {
			if (deviceAction.getActionName().equals(deviceActionName)) {
				return deviceAction;
			}
		}
		return null;
	}
	
	@Override
	public DeviceEvent getDeviceEventByName(String deviceEventName) {
		for (DeviceEvent deviceEvent : deviceEvents) {
			if (deviceEvent.getEventName().equals(deviceEventName)) {
				return deviceEvent;
			}
		}
		return null;
	}

}

