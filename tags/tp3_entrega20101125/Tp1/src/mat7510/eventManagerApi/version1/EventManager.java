package mat7510.eventManagerApi.version1;

import java.util.List;

public interface EventManager extends EventListener {

	public void registerCancellables(Event event1, Event event2) throws RegisterEventException ;

	public void registerEvent(ActionCommand cmd, Event e) throws RegisterEventException ;

	public void registerEventsContinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException ;
	public void registerEventsDiscontinuousWithCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException ;
	public void registerEventsDiscontinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException ;

	public void registerEventsWithOrderContinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException ;
	public void registerEventsWithOrderDiscontinuousWithCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException ;
	public void registerEventsWithOrderDiscontinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException ;

	public void reset();
}
