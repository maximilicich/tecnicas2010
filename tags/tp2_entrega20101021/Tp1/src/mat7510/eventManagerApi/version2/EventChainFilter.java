package mat7510.eventManagerApi.version2;

public abstract class EventChainFilter extends EventChain {
	
	private EventChain filteredChain;
	
	public EventChainFilter(EventChain filteredChain) {
		this.filteredChain = filteredChain;
		// Puenteamos nuestro chain hacia el chain filtrado
		this.setChain(filteredChain.getChain());
		// Lo mismo para el Contexto:
		this.setContext(filteredChain.getContext());
	}
	
	public void eventOccurred(Event e) {
		this.filteredChain.eventOccurred(e);
	}
	
}
