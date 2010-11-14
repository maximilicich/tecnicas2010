package mat7510.smartBuildingDriverLights;


import mat7510.eventManagerApi.version2.Event;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicEvent;
import mat7510.smartBuilding.*;

public class DeviceEventLights implements DeviceEvent {

	private String eventName;
	
	public DeviceEventLights (String eventName){
		this.eventName = eventName;
	}
	
	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof BasicEvent)) {
			return false;
		}
		return ((DeviceEventLights)anotherEvent).eventName.equals(this.eventName);
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