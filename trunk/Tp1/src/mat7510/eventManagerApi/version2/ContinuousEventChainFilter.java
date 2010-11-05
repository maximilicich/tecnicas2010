package mat7510.eventManagerApi.version2;

import java.util.Iterator;

/**
 * 
 * @author Grupo 10
 *
 */
public class ContinuousEventChainFilter extends EventChainFilter {

	/**
	 * 
	 * @param filteredChain
	 */
	public ContinuousEventChainFilter(EventChain filteredChain) {
		super(filteredChain);
	}

	
	/**
	 * Recordar que un filtro ante un evento ocurrido
	 * O bien continua el Circuito (haciendo super.eventOccurred(e)
	 * O bien lo corta (haciendo simplemente return)
	 * 
	 */
	public void eventOccurred(Event e) {
		
		Boolean found = searchEventInChain(e);
		if (found)
			super.eventOccurred(e);
		else
			resetOccurrences();
	}


	/**
	 * 
	 * @param e
	 * @return
	 */
	private Boolean searchEventInChain(Event e) {
		Boolean found = false;
		for (Iterator<Element> iterator = this.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			// Si encontramos el evento en la cadena y aun no ocurrio
			// entonces se cumple la continuidad
			if (element.getEvent().equals(e) && ! element.hasOccurred() ) {
				found = true;
				break;
			}
		}
		return found;
	}
	
}
