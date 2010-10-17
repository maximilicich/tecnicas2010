package mat7510.eventManagerApi.version2;

import java.util.Iterator;

public class ContinuousEventChainFilter extends EventChainFilter {

	public ContinuousEventChainFilter(EventChain filteredChain) {
		super(filteredChain);
	}

	
	/**
	 * Recordar que un filtro ante un evento ocurrido
	 * O bien sigue la cadena (haciendo super.eventOccurred(e)
	 * O bien corta la cadena (haciendo simplemente return)
	 * 
	 */
	public void eventOccurred(Event e) {
		
		Boolean found = false;
		for (Iterator<Element> iterator = this.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			// Si encontramos el evento en la cadena y aun no ocurrio
			// entonces podemos decir que se cumple la continuidad
			if (element.getEvent().equals(e) && ! element.hasOccurred() ) {
				found = true;
				break;
			}
		}
		if (found)
			// Si se cumple la continuidad Seguimos la cadena:
			super.eventOccurred(e);
		else
			// si no se cumple, reseteamos las ocurrencias y CORTAMOS LA CADENA:
			resetOccurrences();
	}
	
	
}
