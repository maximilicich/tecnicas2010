package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import mat7510.eventManagerApi.version1.Event;

public class CambioDePresionDeAguaEvent implements Event {

	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof CambioDePresionDeAguaEvent)) {
			return false;
		}
		// Un cambio de presion es siempre lo mismo...
		return true;	
	}

}
