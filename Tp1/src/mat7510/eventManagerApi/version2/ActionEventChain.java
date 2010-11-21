package mat7510.eventManagerApi.version2;

import java.util.Iterator;

/**
 * 
 * @author Grupo 10
 *
 */
public class ActionEventChain extends EventChain {

	/**
	 * 
	 */
	private ActionCommand action;

	/**
	 * 
	 * @param action
	 */
	public ActionEventChain(ActionCommand action) {
		if (action == null) 
			throw new IllegalArgumentException("Action cannot be null");
		this.action = action;
	}
	
	/**
	 * 
	 */
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

	/**
	 * 
	 * @return
	 */
	public ActionCommand getAction() {
		return action;
	}

	/**
	 * Se permite modificar la accion de esta cadena
	 * 
	 * @param action La nueva accion de la cadena. La accion anterior se descarta
	 */
	public void setAction(ActionCommand action) {
		if (action == null) 
			throw new IllegalArgumentException("Action cannot be null");
		this.action = action;
	}
	
}
