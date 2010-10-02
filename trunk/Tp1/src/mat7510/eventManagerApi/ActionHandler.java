package mat7510.eventManagerApi;

import java.util.Iterator;
import java.util.List;

public class ActionHandler {
	
	private ActionCommand command;
	
	private List<Event> events;
	
	private boolean order;
	
	
	static public ActionHandler createActionSingle ( ActionCommand command, Event event){
		return new ActionHandler(command ,event);
	}
	
	static public ActionHandler createActionGroup ( ActionCommand command, List<Event> event){
		return new ActionHandler(command ,event, false);
	}
	
	static public ActionHandler createActionGroupOrder ( ActionCommand command, List<Event> event){
		return new ActionHandler(command ,event, true);
	}
	
	private ActionHandler ( ActionCommand command, Event event){
		
		this.command = command;
		this.events.add(event);
		setOrder(false);
	}
		
	private ActionHandler ( ActionCommand command, List<Event> events, boolean b){
		
		this.command = command;
		this.events = events;
		setOrder(b);
	}
	    
	
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


	public boolean isOrder() {
		return order;
	}
	
	private void setOrder(boolean b) {
		order = b;		
	}
	

}
