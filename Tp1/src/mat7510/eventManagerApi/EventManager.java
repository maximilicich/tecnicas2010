package mat7510.eventManagerApi;

import java.util.List;

public interface EventManager extends EventListener {

	public void registerCancellables(Event event1, Event event2) throws registerEventException ;

        public void registerEvent(ActionCommand cmd, Event e) throws registerEventException ;

	public void registerEventsContinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws registerEventException ;
	public void registerEventsDiscontinuousWithCancellations(ActionCommand cmd, List<Event> e) throws registerEventException ;
	public void registerEventsDiscontinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws registerEventException ;

	public void registerEventsWithOrderContinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws registerEventException ;
	public void registerEventsWithOrderDiscontinuousWithCancellations(ActionCommand cmd, List<Event> e) throws registerEventException ;
	public void registerEventsWithOrderDiscontinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws registerEventException ;

	public void reset();
}
