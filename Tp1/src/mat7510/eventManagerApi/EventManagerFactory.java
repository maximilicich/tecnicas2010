package mat7510.eventManagerApi;


public class EventManagerFactory {
	private static EventManager eventManager = null;

	private EventManagerFactory(){};

	public static EventManager createEventManager() {
		if (eventManager == null) {
			eventManager = new ConcreteEventManager();
		}
		return eventManager;
	}

	public static EventManager getInstance(){
		if (eventManager == null)
			return createEventManager();

		return eventManager;
	}
}
