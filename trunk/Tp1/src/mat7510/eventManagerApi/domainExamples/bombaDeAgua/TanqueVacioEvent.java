package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import mat7510.eventManagerApi.Event;

public class TanqueVacioEvent implements Event {

	private TanqueDeAgua tanque;
	
	public TanqueVacioEvent(TanqueDeAgua tanque) {
		this.tanque = tanque;
	}
	
	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof TanqueVacioEvent)) {
			return false;
		}
		if (this.tanque.equals(((TanqueVacioEvent)anotherEvent).tanque)) {
			return true;
		}
		return false;
	}
}
