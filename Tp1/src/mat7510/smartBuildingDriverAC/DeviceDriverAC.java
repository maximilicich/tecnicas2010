package mat7510.smartBuildingDriverAC;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mat7510.smartBuilding.DeviceAction;
import mat7510.smartBuilding.DeviceDriver;
import mat7510.smartBuilding.DeviceEvent;

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
		stateMap.put(ATTR_TEMP_AC , "OFF");
		stateMap.put(ATTR_STATE_AC, "20" );
		
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
		
		deviceActions.add(new DeviceActionACTempUp(this, "TEMP UP AC", ATTR_TEMP_AC,"20",deviceEventTempACUp));
		deviceActions.add(new DeviceActionACTempDown(this,"TEMP DOWN AC", ATTR_TEMP_AC,"20",deviceEventTempACDown));
		deviceActions.add(new DeviceActionACFuncFR(this, "FUNCTION AC COLD", ATTR_FUNC_AC,"FR",deviceEventACFuncFR));
		deviceActions.add(new DeviceActionACFuncCL(this,"FUNCTION AC WARM", ATTR_FUNC_AC,"CL",deviceEventACFuncCL));
		deviceActions.add(new DeviceActionACFuncVT(this, "FUNCTION AC VENT", ATTR_FUNC_AC,"VT",deviceEventACFuncVT));
		deviceActions.add(new DeviceActionACOn(this,"TURN ON AC", ATTR_STATE_AC,"ON",deviceEventACOn));
		deviceActions.add(new DeviceActionACOff(this, "TURN OFF AC", ATTR_STATE_AC,"OFF",deviceEventACOff));
		
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

}

