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
		boolean isFound=false;

		while (it.hasNext() && !isFound){
			
			eventCancel = it.next();
			if ( e.equals(eventCancel.getEventSource())){				
				notifyChange ( eventCancel.getEventToBeCancel(),false);
                                isFound=true;
			}			
		}
		
		notifyChange (e,true);
	}

	private void notifyChange(Event event, boolean marcar) {
		
		Iterator<ActionHandler> itActions = actions.iterator();
		Iterator<Event> itEvents;
		ActionHandler action;
		Event actionEvent;
		int index;
	
		while (itActions.hasNext()){
			
			action = itActions.next();	
			itEvents = action.getEventIterator();
			index = 0;

			while (itEvents.hasNext()){
				
				actionEvent = itEvents.next();
				if (event.equals(actionEvent) && marcar == true){
					// marca el evento 
					action.activateEvent(index);
                                        
                                        if (action.isActive()){
                                            action.getCommand().execute();
                                            action.cleanState();
                                        }
                                        return;
				}
				
				if (event.equals(actionEvent) && marcar == false){
					// desmarca el evento
					action.cancelEvent(index);
                                        return;
				}
				index ++;
			}			
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
