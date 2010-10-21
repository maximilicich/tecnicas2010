package mat7510.eventManagerApi.version2;

import java.util.Iterator;
import java.util.List;


public class ActionEventChainFactory {
	
	
	private static ActionEventChainFactory instance = null;
	
	private EventChain eventChain;
	
	private ActionEventChainFactory(){};

	public EventChain createCancellableChain(ActionCommand action, List <Event> events) {
		
		eventChain = new CancellableEventChainFilter(new ActionEventChain(action));

		addEvents(events);
		
		return eventChain;
	}
	
	public EventChain createCancellableContinuosOrderedChain(ActionCommand action, List <Event> events) {
		
		eventChain = new CancellableEventChainFilter(new ContinuousEventChainFilter(new OrderedEventChainFilter(new ActionEventChain(action))));

		addEvents(events);
		
		return eventChain;
	}
	
	public EventChain createContinousOrderedChain(ActionCommand action, List <Event> events) {
		eventChain = new ContinuousEventChainFilter(new OrderedEventChainFilter(new ActionEventChain(action)));

		addEvents(events);
		
		return eventChain;
	}
	
	public EventChain createContinousChain(ActionCommand action, List <Event> events) {
		eventChain = new ContinuousEventChainFilter(new ActionEventChain(action));

		addEvents(events);
		
		return eventChain;
	}
	
	public EventChain createOrderedCancellableChain(ActionCommand action, List <Event> events) {
		eventChain = new OrderedEventChainFilter(new CancellableEventChainFilter(new ActionEventChain(action)));

		addEvents(events);
		
		return eventChain;
	}
	
	public EventChain createOrderedChain(ActionCommand action, List <Event> events) {
		eventChain = new OrderedEventChainFilter(new ActionEventChain(action));

		addEvents(events);
		
		return eventChain;
	}
	
	public EventChain createOrderedContinousCancellableChain(ActionCommand action, List <Event> events) {
		eventChain = new OrderedEventChainFilter(new ContinuousEventChainFilter(new CancellableEventChainFilter(new ActionEventChain(action))));

		addEvents(events);
		
		return eventChain;
	}
	
	public EventChain createOrderedContinousChain(ActionCommand action, List <Event> events) {
		eventChain = new OrderedEventChainFilter(new ContinuousEventChainFilter(new ActionEventChain(action)));

		addEvents(events);
		
		return eventChain;
	}
	
	public EventChain createSimpleChain(ActionCommand action, List <Event> events) {
		eventChain = new ActionEventChain(action);

		addEvents(events);
		
		return eventChain;
	}

	public static ActionEventChainFactory getInstance(){
		if (instance == null)
			instance = new ActionEventChainFactory();
		return instance;
	}
	
	private void addEvents(List<Event> events) {
		for (Iterator<Event> iterator = events.iterator(); iterator.hasNext();) {
			eventChain.addEvent(iterator.next());
		}
	}
	

}
