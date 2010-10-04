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
/* //Version mutuamente cancelable
public class EventCancel {
	
	private Event eventSource1;	
	private Event eventSource2;

	public EventCancel ( Event eventSource1 , Event eventSource2){
		this.eventSource1 = eventSource1;
		this.eventSource2 = eventSource2;
	}

        public boolean isEventCancelable(Event event){

            if(eventSource2.equals(event) || eventSource1.equals(event))
                return true;

            return false;
        }


	public Event getEventToBeCancel(Event event) {

                if(eventSource2.equals(event))
                    return eventSource1;
                else if(eventSource1.equals(event))
                    return eventSource2;
                
		return null;
	}


}
*/