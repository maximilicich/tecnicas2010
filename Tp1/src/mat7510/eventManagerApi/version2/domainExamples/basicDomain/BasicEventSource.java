package mat7510.eventManagerApi.version2.domainExamples.basicDomain;

import mat7510.eventManagerApi.version2.EventListener;


public class BasicEventSource {
	
	private String eventToTrigger;
	
	public BasicEventSource(String eventToTrigger) {
		this.eventToTrigger = eventToTrigger;
	}
	
	private EventListener listener;
	public void addListener(EventListener listener) {
		this.listener = listener;
	}
	
	public void triggerEvent()  {
		listener.eventOccurred(new BasicEvent(eventToTrigger));
	}
	
	public void triggerEventNull() {
		BasicEvent event = null;
		listener.eventOccurred(event);
	}
}
