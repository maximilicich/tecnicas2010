package mat7510.eventManagerApi;

import java.util.List;

public interface EventManager extends EventListener {

	public void register(ActionCommand cmd, Event e);
	public void register(ActionCommand cmd, List<Event> e);
	public void registerWithOrder(ActionCommand cmd, List<Event> e);
	public void registerCancellables(Event event1, Event event2	);
	
}
