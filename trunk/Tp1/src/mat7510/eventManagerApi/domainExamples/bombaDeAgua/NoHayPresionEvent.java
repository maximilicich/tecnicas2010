package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import mat7510.eventManagerApi.Event;

public class NoHayPresionEvent implements Event {
	
	private MedidorDePresionDeAgua medidor;
	
	public NoHayPresionEvent(MedidorDePresionDeAgua medidor) {
		this.medidor = medidor;
	}
	
	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof NoHayPresionEvent)) {
			return false;
		}
		if (this.medidor.equals(((NoHayPresionEvent)anotherEvent).medidor)) {
			return true;
		}
		return false;
	}
}
