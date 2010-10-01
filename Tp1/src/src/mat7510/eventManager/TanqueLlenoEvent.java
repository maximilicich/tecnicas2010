package mat7510.eventManager;

import mat7510.eventManagerApi.Event;

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
