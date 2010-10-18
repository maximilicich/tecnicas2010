package mat7510.eventManagerApi.version2;

import java.util.Iterator;

/**
 * 
 * @author Grupo 10 
 *
 */
public class OrderedEventChainFilter extends EventChainFilter {

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
		// Nos vamos reseteando la  
		if (! iterator.hasNext()) {
			resetOccurrences();
			return;
		}
		
		// Ahora buscamos el proximo evento esperado 
		// (el primero aun no occurrido) 
		Event nextEventExpected = null;
		while (iterator.hasNext() && nextEventExpected == null) {
			Element element = iterator.next();
			if (! element.hasOccurred())
				nextEventExpected = element.getEvent();
		}
		
		// Si no hay proximo evento esperado (ya occurrieron todos)
		// El orden se cumple...(?)
		// Entonces Nos vamos siguiendo la cadena
		if (nextEventExpected == null) {
			super.eventOccurred(e);
			return;
		}
		
		// Si el proximo evento esperado es el evento occurrido
		// Entonces se cumple el Orden:
		// Nos vamos siguiendo la cadena
		if (nextEventExpected.equals(e)) {
			super.eventOccurred(e);
			return;
		}
		
		// Sino, verificamos que ninguno de los proximos eventos esperados
		// sea el ocurrido
		// si asi fuera, entonces no se cumple el Orden
		while (iterator.hasNext()) {
			Element element = iterator.next();
			if (element.getEvent().equals(e)) {
				// El evento ocurrido esta entre los esperados
				// Pero No se cumple el orden!
				// Reseteamos las occurrencias y nos vamos cortando la cadena!
				resetOccurrences();
				return;
			}
			if (element.hasOccurred()) {
				// Caso especial:
				// Encontramos un evento marcado como ocurrido
				// Luego de un evento no ocurrido
				// Esto es señal de que la cadena NO esta marcada En Orden
				// Reseteamos las occurrencias y nos vamos cortando la cadena!
				resetOccurrences();
				return;
			}
		}
		
		// Si llegamos aca, entonces El evento ocurrido No esta entre los esperados
		// Asi, Seguimos la cadena...
		super.eventOccurred(e);
		
	}

	
	
}
