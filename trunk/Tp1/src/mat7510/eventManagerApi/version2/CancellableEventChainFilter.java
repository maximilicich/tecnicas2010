package mat7510.eventManagerApi.version2;

import java.util.Iterator;
import java.util.List;


/**
 * 
 * @author Grupo 10 
 *
 */
public class CancellableEventChainFilter extends EventChainFilter {

	public CancellableEventChainFilter(EventChain filteredChain) {
		super(filteredChain);
	}

	
	/**
	 * Recordar que un filtro ante un evento ocurrido
	 * O bien sigue el Circuito (haciendo super.eventOccurred(e)
	 * O bien lo corta (haciendo simplemente return)
	 * 
	 */
	public void eventOccurred(Event e) {
		
		// Si no tenemos Contexto, no tenemos cancellables definidos
		if (getContext() == null) {
			super.eventOccurred(e);
			return;
		}
		
		List<Event> cancellables = getContext().getCancellablesFor(e);
		
		if (cancellables == null || cancellables.isEmpty()) {
			super.eventOccurred(e);
			return;
		}
		
		Boolean hasCancelled = searchAndExecuteCancellables(cancellables);
		
		//El cancelable no esta en la cadena o bien aun no ocurrio, se continua el circuito
		if (! hasCancelled)
			super.eventOccurred(e);
		
	}


	private Boolean searchAndExecuteCancellables(List<Event> cancellables) {
		Boolean hasCancelled = false;
		for (Event cancellableEvent : cancellables) {
			for (Iterator<Element> it = iterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getEvent().equals(cancellableEvent) && element.hasOccurred()) {
					element.setOccurred(false);
					hasCancelled = true;
				}
			}
		}
		return hasCancelled;
	}
	
	
}
