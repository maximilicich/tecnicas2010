package mat7510.eventManagerApi.version2;

public interface EventContext {

	public void setCancellableFor(Event canceller, Event cancelled);
	public Event getCancellableFor(Event canceller);
	
}
