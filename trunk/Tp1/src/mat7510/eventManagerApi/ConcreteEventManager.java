package mat7510.eventManagerApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcreteEventManager implements EventManager {

	private List<ActionHandler> actions;
	
	private List<EventCancel> eventsCancel;
	
	
	public ConcreteEventManager (){
		actions = new ArrayList<ActionHandler>();
		eventsCancel = new ArrayList<EventCancel>();
	}
	
	
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
                        /* //Version eventos mutuamente cancelables
			if ( eventCancel.isEventCancelable(e) ){
				notifyChange ( eventCancel.getEventToBeCancel(e),false);
                                isFound=true;			
                        }*/
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

                //TODO: ver de lanzar exepciones o que hacer en estos casos
                if(event1.equals(event2))
                    return;

		EventCancel eventCancel = new EventCancel (event1, event2);
		this.eventsCancel.add(eventCancel);
	}

	@Override
	public void reset() {
		// TODO Implementar el reset(). Deberia desregistrar todos los eventos-comandos y cancelables, dejando al manager en su estado inicial...
		
	}

}