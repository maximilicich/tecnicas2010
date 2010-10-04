package mat7510.eventManagerApi;

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