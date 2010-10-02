package mat7510.eventManagerApi;

import java.util.List;

public class ConcreteEventManager implements EventManager {

	
	
	private List<Action> actions;
	
	
	@Override
	public void eventOccurred(Event e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void register(ActionCommand cmd, Event e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void register(ActionCommand cmd, List<Event> e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerWithOrder(ActionCommand cmd, List<Event> e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerCancellables(Event event1, Event event2) {
		// TODO Auto-generated method stub

	}

}
