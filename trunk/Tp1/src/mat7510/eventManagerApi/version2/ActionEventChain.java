package mat7510.eventManagerApi.version2;

import java.util.Iterator;

/**
 * 
 * @author Grupo 10
 *
 */
public class ActionEventChain extends EventChain {

	private ActionCommand action;

	public ActionEventChain(ActionCommand action) {
		this.action = action;
	}
	
	public void eventOccurred(Event e) {
		for (Iterator<Element> iterator = this.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			if (element.getEvent().equals(e)) {
				element.setOccurred(true);
				break;
			}
		}
		if (isAccomplished()) {
			action.execute();
			resetOccurrences();
		}
	}

	public ActionCommand getAction() {
		return action;
	}

}
