package mat7510.eventManagerApi.domainExamples.basicDomain;

import mat7510.eventManagerApi.EventListener;

public class BasicEventSource {
	
	private String eventToTrigger;
	
	public BasicEventSource(String eventToTrigger) {
		this.eventToTrigger = eventToTrigger;
	}
	
	private EventListener listener;
	public void addListener(EventListener listener) {
		this.listener = listener;
	}
	
	public void triggerEvent() {
		listener.eventOccurred(new BasicEvent(eventToTrigger));
	}
}
