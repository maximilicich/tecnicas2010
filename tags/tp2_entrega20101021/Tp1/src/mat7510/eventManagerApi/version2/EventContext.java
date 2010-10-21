package mat7510.eventManagerApi.version2;

import java.util.List;

public interface EventContext {

	public void setCancellableFor(Event canceller, Event cancelled);
	public List<Event> getCancellablesFor(Event canceller);
	public void registerEventChain(EventChain eventChain);
	public void reset();
}
