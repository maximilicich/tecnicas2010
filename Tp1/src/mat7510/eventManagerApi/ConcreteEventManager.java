package mat7510.eventManagerApi;

import java.util.Iterator;
import java.util.List;

public class ConcreteEventManager implements EventManager {

	
	
	private List<ActionHandler> actions;
	
	private List<EventCancel> eventsCancel;
	
	@Override
	public void eventOccurred(Event e) {
		
		Iterator<EventCancel> it = eventsCancel.iterator();
		EventCancel eventCancel;
		
		while (it.hasNext()){
			
			eventCancel = it.next();
			if ( e.equals(eventCancel.getEventSource())){				
				notifyChange ( eventCancel.getEventToBeCancel(),false);
			}			
		}
		
		notifyChange (e,true);

	}

	private void notifyChange(Event event, boolean b) {
		
		Iterator<ActionHandler> itActions = actions.iterator();
		Iterator<Event> itEvents;
		ActionHandler action;
		
		while (itActions.hasNext()){
			
			action = itActions.next();	
			itEvents = action.getEventIterator();
			while (itEvents.hasNext()){
				
				if (event.equals(itEvents.next()) && b == true){
					// marca el evento
				}
				
				if (event.equals(itEvents.next()) && b == false){
					// desmarca el evento
				}
			}
			
			// Si estan todos marcados
					//action.getCommand().execute();
					//desmarcar eventos 
			// fin si
			
		}
		
	}

	@Override
	public void register(ActionCommand cmd, Event e) {
		
		ActionHandler action = ActionHandler.createActionSingle (cmd, e);
		this.actions.add(action);

	}

	@Override
	public void register(ActionCommand cmd, List<Event> e) {
		
		ActionHandler action = ActionHandler.createActionGroup  (cmd, e);
		this.actions.add(action);

	}

	@Override
	public void registerWithOrder(ActionCommand cmd, List<Event> e) {
		ActionHandler action = ActionHandler.createActionGroupOrder  (cmd, e);
		this.actions.add(action);

	}

	@Override
	public void registerCancellables(Event event1, Event event2) {
		
		EventCancel eventCancel = new EventCancel (event1, event2);
		this.eventsCancel.add(eventCancel);
	}
	
		

}
