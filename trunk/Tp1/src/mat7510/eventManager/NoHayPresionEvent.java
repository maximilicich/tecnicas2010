package mat7510.eventManager;

import mat7510.eventManagerApi.Event;

public class NoHayPresionEvent implements Event {
	
	private MedidorDePresionDeAgua medidor;
	
	public NoHayPresionEvent(MedidorDePresionDeAgua medidor) {
		this.medidor = medidor;
	}
	
	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof HayPresionEvent)) {
			return false;
		}
		if (this.medidor.equals(((NoHayPresionEvent)anotherEvent).medidor)) {
			return true;
		}
		return false;
	}
}
