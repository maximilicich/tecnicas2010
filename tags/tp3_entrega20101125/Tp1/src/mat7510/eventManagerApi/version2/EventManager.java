package mat7510.eventManagerApi.version2;

import java.util.ArrayList;
import java.util.List;


/**
 * Es un Singleton
 * 
 * @author Grupo 10 
 */
public class EventManager implements EventContext {
	
	/**
	 * 
	 */
	private static EventManager instance = null;

	/**
	 * 
	 */
	private List<EventChain> chains = new ArrayList<EventChain>();
	
	/**
	 * 
	 */
	private List<CancellablePair> cancellables = new ArrayList<CancellablePair>();
	
	/**
	 * SINGLETON: Constructor Privado
	 */
	private EventManager(){};

	/**
	 * SINGLETON: getInstance()
	 * @return
	 */
	public static EventManager getInstance(){
		if (instance == null)
			instance = new EventManager();
		return instance;
	}
	


	@Override
	/**
	 * Distribuye el evento ocurrido entre sus EventChains
	 * Nada mas...
	 */
	public void eventOccurred(Event e) {
		for (EventChain chain : chains) {
			chain.eventOccurred(e);
		}
	}

	@Override
	/**
	 * Devuelve lista vacia si no hay ninguno...
	 */
	public List<Event> getCancellablesFor(Event canceller) {
		List<Event> cancels = new ArrayList<Event>();
		for(CancellablePair pair : cancellables) {
			if (pair.getCanceller().equals(canceller)) {
				cancels.add(pair.getCancellable());
			}
		}
		return cancels;
}

	@Override
	/**
	 * 
	 */
	public void setCancellableFor(Event canceller, Event cancellable) {
		CancellablePair pair = new CancellablePair(canceller, cancellable);
		cancellables.add(pair);
	}

	@Override
	/**
	 * Registra un EventChain como un Listener
	 * De manera que le comunica luego los Eventos a medida que ocurren
	 * El EventChain recibe a este Manager como Contexto 
	 */
	public void registerEventChain(EventChain eventChain) {
		chains.add(eventChain);
		eventChain.setContext(this);
	}
	
	
	/**
	 * Operacion inversa a registerEventChain
	 * 
	 * @param eventChain
	 * @return true si pudo desregistrar. false en caso contrsrio
	 */
	public boolean unregisterEventChain(EventChain eventChain) {
		Boolean res = chains.remove(eventChain);
		if (res)
			eventChain.setContext(null);
		return res;
	}
	
	@Override
	/**
	 * 
	 */
	public void reset() {
		chains.clear();
		cancellables.clear();
	}
	
	@Override
	/**
	 * 
	 */
	public List<EventChain> getRegisteredEventChains() {
		return this.chains;
	}
	

	/**
	 * 
	 * Inner Class para representar los pares cancelables
	 * 
	 * @author Grupo 10
	 *
	 */
	public class CancellablePair {
		private Event canceller;
		private Event cancellable;
		
		public CancellablePair(Event canceller, Event cancellable) {
			this.canceller = canceller;
			this.cancellable = cancellable;
		}
		
		public void setCanceller(Event canceller) {
			this.canceller = canceller;
		}
		public Event getCanceller() {
			return canceller;
		}
		public void setCancellable(Event cancellable) {
			this.cancellable = cancellable;
		}
		public Event getCancellable() {
			return cancellable;
		}
	}



}
