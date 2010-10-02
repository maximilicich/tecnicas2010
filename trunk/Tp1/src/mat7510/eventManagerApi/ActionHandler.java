package mat7510.eventManagerApi;

import java.util.Iterator;
import java.util.List;

public class ActionHandler {
	
	private ActionCommand command;
	
	private List<Event> events;
	
	private List<Integer> eventsIndexs;
	
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
		clearEvents();
	}
		
	private ActionHandler ( ActionCommand command, List<Event> events, boolean order){
		
		this.command = command;
		this.events = events;
		setOrder(order);
		clearEvents();
	}	    
	
	private void clearEvents() {		
	// Inicializa la lista de eventos de control del comando
		
		for (int index = 0; index < events.size(); index++ ){
			eventsIndexs.add(index);
		}		
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
	
	private void setOrder(boolean order) {
		this.order = order;		
	}
	
	public void activateEvent (int index){
	// Cuando se activa se remueve de la lista el numero de evento correspondiente al indice	
		
		if ( order == true ){
			if (verifyState( index ) == true && verifyOrder( index ) == true ){
				eventsIndexs.remove(index);
			}
		}	
		else{			
			if (verifyState( index ) == true){
				eventsIndexs.remove(index);
			}	
		}		
	}
	
	public void cancelEvent (int index){
	// Cuando se cancela se vuelve a agregar a la lista el numero de evento correspondiente al indice
		
			if (verifyState(index) == false){
				eventsIndexs.add(index,index);
			}	
	}
	
	private boolean verifyOrder(int index) {
	// Verifica que no haya ningun evento con indice menor al que se esta revisando dentro de la lista	
		
		Iterator<Integer>it = eventsIndexs.iterator();
		boolean doSomething = true;
		
		while (it.hasNext() && doSomething == true ){
			
			if ( it.next() < index ){
				doSomething = false;
			}		
		}
		return doSomething;			
	}
	
	private boolean verifyState( int index ){
	// Verifica que ya no se pueda activar/desactivar el mismo evento en forma sucesiva
		
		Iterator<Integer>it = eventsIndexs.iterator();
		boolean doSomething = false;
		
		while (it.hasNext() && doSomething == true ){
			
			if ( it.next() == index ){
				doSomething = true;
			}		
		}
		return doSomething;
	}
	
	public boolean isActive() {	
	// Verifica que se encuentren todos los eventos activados osea borrados de la lista de indice de eventos	
			return eventsIndexs.isEmpty();		
	}
	
	public void cleanState(){
	// Vuelve los eventos al momento inicial	
			clearEvents();		
	}

}
