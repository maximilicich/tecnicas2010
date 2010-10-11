package mat7510.eventManagerApi;

/**
 * 
 *
 */
public class EventManagerFactory {
	
	private static EventManagerFactory instance = null;

	private EventManagerFactory(){};

	public EventManager createEventManager() {
		return new ConcreteEventManager();
	}

	public static EventManagerFactory getInstance(){
		if (instance == null)
			instance = new EventManagerFactory();
		return instance;
	}
}
