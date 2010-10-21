package mat7510.eventManagerApi.domainExamples.basicDomain;

import mat7510.eventManagerApi.EventListener;
import mat7510.eventManagerApi.exceptionRegisterEvent;

public class BasicEventSource {
	
	private String eventToTrigger;
	
	public BasicEventSource(String eventToTrigger) {
		this.eventToTrigger = eventToTrigger;
	}
	
	private EventListener listener;
	public void addListener(EventListener listener) {
		this.listener = listener;
	}
	
	public void triggerEvent() throws exceptionRegisterEvent {
		listener.eventOccurred(new BasicEvent(eventToTrigger));
	}
	
	public void triggerEventNull() throws exceptionRegisterEvent {
		BasicEvent event = null;
		listener.eventOccurred(event);
	}
}
