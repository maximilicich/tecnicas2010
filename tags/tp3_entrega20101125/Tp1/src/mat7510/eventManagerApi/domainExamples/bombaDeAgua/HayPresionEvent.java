package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import mat7510.eventManagerApi.version1.Event;

public class HayPresionEvent implements Event {
	
	private MedidorDePresionDeAgua medidor;
	
	public HayPresionEvent(MedidorDePresionDeAgua medidor) {
		this.medidor = medidor;
	}
	
	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof HayPresionEvent)) {
			return false;
		}
		if (this.medidor.equals(((HayPresionEvent)anotherEvent).medidor)) {
			return true;
		}
		return false;
	}
}
