package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import java.math.BigDecimal;

import mat7510.eventManagerApi.EventListener;

public class TanqueDeAgua {

	private EventListener eventListener;

	public void addEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}

	public void addAgua(BigDecimal litros) {
		// lalala
		if(isTanqueLleno()) {
			// hace algo...
			// e informa el evento !!
			eventListener.eventOccurred(new TanqueLlenoEvent(this));
		}
	}
	
	public void substractAgua(BigDecimal litros) {
		// lalala
		if(isTanqueVacio()) {
			// hace algo...
			// e informa el evento !!
			eventListener.eventOccurred(new TanqueVacioEvent(this));
		}
	}
	
	public boolean isTanqueLleno() {
		// if lalalala..
		return false;
	}

	public boolean isTanqueVacio() {
		// if lalalala..
		return false;
	}

}
