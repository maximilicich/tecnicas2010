package mat7510.eventManagerApi;

import java.util.List;

public interface EventManager extends EventListener {

	public void register(ActionCommand cmd, Event e) throws exceptionRegisterEvent ;
	public void register(ActionCommand cmd, List<Event> e) throws exceptionRegisterEvent ;
	public void registerWithOrder(ActionCommand cmd, List<Event> e) throws exceptionRegisterEvent ;
	public void registerCancellables(Event event1, Event event2) throws exceptionRegisterEvent ;

	public void reset();
}
