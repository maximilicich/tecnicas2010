package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import mat7510.eventManagerApi.Event;

public class CambioDePresionDeAguaEvent implements Event {

	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof CambioDePresionDeAguaEvent)) {
			return false;
		}
		if (this.equals(((CambioDePresionDeAguaEvent)anotherEvent))) {
			return true;
		}
		return false;	
	}

}
