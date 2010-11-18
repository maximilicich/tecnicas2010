package mat7510.eventManagerApi.domainExamples.basicDomain;

import mat7510.eventManagerApi.version1.Event;

public class BasicEvent implements Event {

	private String eventName;

	public BasicEvent(String eventName) {
		this.eventName = eventName;
	}

	public String getEventName() {
		return this.eventName;
	}

	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof BasicEvent)) {
			return false;
		}
		return ((BasicEvent)anotherEvent).eventName.equals(this.eventName);
	}

	@Override
	public String toString() {
		return this.eventName;
	}

}

