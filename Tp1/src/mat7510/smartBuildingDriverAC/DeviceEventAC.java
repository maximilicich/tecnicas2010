package mat7510.smartBuildingDriverAC;

import mat7510.eventManagerApi.version2.Event;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicEvent;
import mat7510.smartBuilding.*;

public class DeviceEventAC implements DeviceEvent {

	private String eventName;
	
	public DeviceEventAC (String eventName){
		this.eventName = eventName;
	}
	
	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof BasicEvent)) {
			return false;
		}
		return ((DeviceEventAC)anotherEvent).eventName.equals(this.eventName);
	}

	@Override
	public String getEventName() {
		return this.eventName;
	}
	
	@Override
	public String toString() {
		return this.eventName;
	}

}
