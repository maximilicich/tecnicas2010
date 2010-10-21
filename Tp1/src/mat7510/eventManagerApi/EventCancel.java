package mat7510.eventManagerApi;

public class EventCancel {

	private Event eventSource;

	private Event eventToBeCancel;

	public EventCancel ( Event eventSource , Event eventToBeCancel){
		this.eventSource = eventSource;
		this.eventToBeCancel = eventToBeCancel;
	}

	public void setEventToBeCancel(Event eventToBeCancel) {
		this.eventToBeCancel = eventToBeCancel;
	}

	public Event getEventToBeCancel() {
		return eventToBeCancel;
	}

	public void setEventSource(Event eventSource) {
		this.eventSource = eventSource;
	}

	public Event getEventSource() {
		return eventSource;
	}

}
