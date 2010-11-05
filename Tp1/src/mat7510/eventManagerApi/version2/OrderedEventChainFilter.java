package mat7510.eventManagerApi.version2;

import java.util.Iterator;

/**
 * 
 * @author Grupo 10 
 *
 */
public class OrderedEventChainFilter extends EventChainFilter {

	/**
	 * 
	 * @param filteredChain
	 */
	public OrderedEventChainFilter(EventChain filteredChain) {
		super(filteredChain);
	}

	/**
	 * Recordar que un filtro ante un evento ocurrido
	 * O bien sigue el Circuito (haciendo super.eventOccurred(e)
	 * O bien lo corta (haciendo simplemente return)
	 * 
	 */
	public void eventOccurred(Event e) {

		Iterator<Element> iterator = this.iterator();
		
		// Si la cadena esta vacia, entonces no se cumple el orden
		if (! iterator.hasNext()) {
			resetOccurrences();
			return;
		}
		
		Event nextEventExpected = searchNextEventExpected(iterator);
		
		// Si no hay proximo evento esperado (ya occurrieron todos)
		if (nextEventExpected == null) {
			super.eventOccurred(e);
			return;
		}
		
		// Si el proximo evento esperado es el evento occurrido
		if (nextEventExpected.equals(e)) {
			super.eventOccurred(e);
			return;
		}
		
		// Se verifica que ninguno de los proximos eventos esperados sea el ocurrido
		// si asi fuera, entonces no se cumple el Orden
		while (iterator.hasNext()) {
			Element element = iterator.next();
			if (element.getEvent().equals(e)) {
				// El evento ocurrido esta entre los esperados pero no cumple el orden
				resetOccurrences();
				return;
			}
			if (element.hasOccurred()) {
				// Evento marcado como ocurrido luego de un evento no ocurrido
				// La cadena NO esta marcada En Orden
				resetOccurrences();
				return;
			}
		}
		
		// El evento ocurrido No esta entre los esperados
		super.eventOccurred(e);
		
	}

	/**
	 * 
	 * @param iterator
	 * @return
	 */
	private Event searchNextEventExpected(Iterator<Element> iterator) {
		Event nextEventExpected = null;
		while (iterator.hasNext() && nextEventExpected == null) {
			Element element = iterator.next();
			if (! element.hasOccurred())
				nextEventExpected = element.getEvent();
		}
		return nextEventExpected;
	}

	}
