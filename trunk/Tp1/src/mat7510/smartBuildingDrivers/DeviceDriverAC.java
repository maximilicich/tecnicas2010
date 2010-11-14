package mat7510.smartBuildingDrivers;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mat7510.eventManagerApi.version2.EventListener;
import mat7510.smartBuilding.DeviceAction;
import mat7510.smartBuilding.DeviceDriver;
import mat7510.smartBuilding.DeviceEvent;

public class DeviceDriverAC implements DeviceDriver{
	
	private List<DeviceAction> deviceActions;
	private List<DeviceEvent>  deviceEvents;
	private Map<String,String> stateMap;
	private List<EventListener> listeners;
	private String tempMax;
	private String tempMin;
	
	
	public DeviceDriverAC(){
		
		stateMap = new LinkedHashMap<String, String>();
		stateMap.put("FuncAc", "FR");
		stateMap.put("EncendidoAC", "OFF");
		stateMap.put("TempAc", "20");
		
		deviceActions = new ArrayList<DeviceAction>();
		deviceActions.add(new DeviceActionACTempUp(this));
		deviceActions.add(new DeviceActionACTempDown(this));
		deviceActions.add(new DeviceActionACFuncFR(this));
		deviceActions.add(new DeviceActionACFuncCL(this));
		deviceActions.add(new DeviceActionACFuncVT(this));
		deviceActions.add(new DeviceActionACOn(this));
		deviceActions.add(new DeviceActionACOff(this));
		
		deviceEvents = new ArrayList<DeviceEvent>();
	    
	    listeners = new ArrayList<EventListener>();
	    
	    tempMax = "28";
	    tempMin = "17";
		
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
	
	@Override
	public void addEventListener(EventListener eventListener) {
		
		this.listeners.add(eventListener);
		
	}
	
	public String getTempMax(){
		return tempMax;
	}
	
	public String getTempMin(){
		return tempMin;
	}

}

