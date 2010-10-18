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
		// Entonces nos vamos siguiendo la cadena
		if (getContext() == null) {
			super.eventOccurred(e);
			return;
		}
		
		// Le preguntamos al Contexto si tenemos cancellables
		// Para el evento que ocurre
		List<Event> cancellables = getContext().getCancellablesFor(e);
		
		// Si no hay, nos vamos delegando el evento en el circuito:
		if (cancellables == null || cancellables.isEmpty()) {
			super.eventOccurred(e);
			return;
		}
		
		// ahora, si tenemos un cancellable, lo buscamos en la cadena
		// Si lo encontramos y ya habia ocurrido, lo CANCELAMOS
		// es decir, lo marcamos como NO OCURRIDO
		// Y asimismo CORTAMOS EL CIRCUITO
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
		
		// Si estamos aca, o bien el cancellable no esta en la cadena
		// o bien aun no ocurrio
		// Entonces continuamos el circuito, delegando el evento
		if (! hasCancelled)
			super.eventOccurred(e);
		
	}
	
	
}
