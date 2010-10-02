package mat7510.eventManagerApi;

import java.util.Iterator;
import java.util.List;

public class ActionHandler {
	
	private ActionCommand command;
	
	private List<Event> events;
	
	
	public void addEvento( Event event) {
		this.events.add(event);
	}

	public Iterator<Event> getEventIterator(){
		return this.events.listIterator();
	}

	public void setCommand(ActionCommand command) {
		this.command = command;
	}

	public ActionCommand getCommand() {
		return command;
	}
	
	

}
