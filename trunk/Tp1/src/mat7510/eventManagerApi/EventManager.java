package mat7510.eventManagerApi;

import java.util.List;

public interface EventManager extends EventListener {

    	public void registerCancellables(Event event1, Event event2) throws exceptionRegisterEvent ;

	public void registerEventWithCancellations(ActionCommand cmd, Event e) throws exceptionRegisterEvent ;
        public void registerEventWithNoCancellations(ActionCommand cmd, Event e) throws exceptionRegisterEvent ;
        
	public void registerEventsContinuousWithCancellations(ActionCommand cmd, List<Event> e) throws exceptionRegisterEvent ;
        public void registerEventsContinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws exceptionRegisterEvent ;
        public void registerEventsDiscontinuousWithCancellations(ActionCommand cmd, List<Event> e) throws exceptionRegisterEvent ;
        public void registerEventsDiscontinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws exceptionRegisterEvent ;

	public void registerEventsWithOrderContinuousWithCancellations(ActionCommand cmd, List<Event> e) throws exceptionRegisterEvent ;
        public void registerEventsWithOrderContinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws exceptionRegisterEvent ;
        public void registerEventsWithOrderDiscontinuousWithCancellations(ActionCommand cmd, List<Event> e) throws exceptionRegisterEvent ;
        public void registerEventsWithOrderDiscontinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws exceptionRegisterEvent ;

	public void reset();
}
