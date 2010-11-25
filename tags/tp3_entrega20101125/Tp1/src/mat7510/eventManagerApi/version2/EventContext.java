package mat7510.eventManagerApi.version2;

import java.util.List;

/**
 * 
 * @author Grupo 10 
 *
 */
public interface EventContext extends EventListener {

	/**
	 * 
	 * @param canceller
	 * @param cancelled
	 */
	public void setCancellableFor(Event canceller, Event cancelled);
	
	/**
	 * 
	 * @param canceller
	 * @return
	 */
	public List<Event> getCancellablesFor(Event canceller);
	
	/**
	 * 
	 * @param eventChain
	 */
	public void registerEventChain(EventChain eventChain);
	
	
	/**
	 * 
	 * @return
	 */
	public List<EventChain> getRegisteredEventChains();
	
	/**
	 * 
	 */
	public void reset();
}
