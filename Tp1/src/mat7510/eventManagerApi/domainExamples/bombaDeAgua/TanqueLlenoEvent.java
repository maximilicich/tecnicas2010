package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import mat7510.eventManagerApi.version1.Event;

public class TanqueLlenoEvent implements Event {

	private TanqueDeAgua tanque;
	
	public TanqueLlenoEvent(TanqueDeAgua tanque) {
		this.tanque = tanque;
	}
	
	@Override
	public boolean equals(Event anotherEvent) {
		if (!(anotherEvent instanceof TanqueLlenoEvent)) {
			return false;
		}
		if (this.tanque.equals(((TanqueLlenoEvent)anotherEvent).tanque)) {
			return true;
		}
		return false;
	}
}
