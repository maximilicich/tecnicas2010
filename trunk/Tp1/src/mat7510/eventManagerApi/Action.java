package mat7510.eventManagerApi;

import java.util.Iterator;
import java.util.List;

public class Action {
		
	private List<Event> events;
	
	private List<EventCancel> eventsCancel;
	
	public void addEvento( Event event) {
		this.events.add(event);
	}

	public Iterator<Event> getEventIterator(){
		return this.events.listIterator();
	}
	
	
	public void addEventCancel( EventCancel event) {
		this.eventsCancel.add(event);
	}

	public Iterator<EventCancel> getEventCancelIterator(){
		return this.eventsCancel.listIterator();
	}
	
		

}
