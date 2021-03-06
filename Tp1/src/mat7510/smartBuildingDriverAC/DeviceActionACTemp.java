package mat7510.smartBuildingDriverAC;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mat7510.smartBuilding.domain.devicedriver.DeviceAction;
import mat7510.smartBuilding.domain.devicedriver.DeviceEventListener;

public abstract class DeviceActionACTemp extends DeviceAction{
	
	private DeviceDriverAC deviceDriverAC;
	private String actionName;
	
	private String attr;
	private String value;
	private DeviceEventAC event;

	
	public DeviceActionACTemp ( DeviceDriverAC deviceDriverAC, 
			String actionName, 
			String attr,
			String value,
			DeviceEventAC event) {

		super(deviceDriverAC, actionName);

		this.deviceDriverAC = deviceDriverAC;
		this.actionName = actionName;
		this.setAttr(attr);
		this.setValue(value);
		this.setEvent(event);
	}

	@Override
	public void execute() {
		
		Map<String,String> stateMap = getDeviceDriverAC().getState();
		
		Iterator<Entry<String, String>> itStateMap = stateMap.entrySet().iterator();
		
		while (itStateMap.hasNext()) {
			Map.Entry<String, String> elem = (Map.Entry<String, String>)itStateMap.next();
			if ( this.getAttr() == elem.getKey() ){
				modifyTemp ( elem );
				for (DeviceEventListener listener : deviceDriverAC.getEventListeners()) {
					listener.eventOccurred(event);
				}
			}
		}
	}

	@Override
	public String getActionName() {
		return actionName;
	}
	
	
	public abstract void modifyTemp (Map.Entry<String, String> mapEntry);
	

	public void setDeviceDriverAC(DeviceDriverAC deviceDriverAC) {
		this.deviceDriverAC = deviceDriverAC;
	}

	public DeviceDriverAC getDeviceDriverAC() {
		return deviceDriverAC;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getAttr() {
		return attr;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setEvent(DeviceEventAC event) {
		this.event = event;
	}

	public DeviceEventAC getEvent() {
		return event;
	}

}