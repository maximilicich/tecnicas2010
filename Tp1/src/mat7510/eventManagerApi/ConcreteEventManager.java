package mat7510.eventManagerApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcreteEventManager implements EventManager {

	private List<ActionHandler> actions;

	private List<EventCancel> eventsCancel;


        private void EnsureEventIsNotNull(Event e)throws registerEventException{
        	if(e==null)
			throw new registerEventException("Evento nulo");
        }

        private void EnsureCommandIsNotNull(ActionCommand cmd)throws registerEventException{
        	if(cmd==null)
			throw new registerEventException("Comando nulo");
        }

	public ConcreteEventManager (){
		actions = new ArrayList<ActionHandler>();
		eventsCancel = new ArrayList<EventCancel>();
	}


        private void notifyCancelEvent(Event e){
        	Iterator<EventCancel> it = eventsCancel.iterator();
		EventCancel eventCancel;

		while (it.hasNext()){

			eventCancel = it.next();
			if ( e.equals(eventCancel.getEventSource())){
				notifyChange ( eventCancel.getEventToBeCancel(),false);
				break;
			}
		}

        }

	public void eventOccurred(Event e) {

		if(e==null)
			throw new NullPointerException("Evento nulo");

                notifyCancelEvent( e);
		notifyChange (e,true);
	}

	private void notifyChange(Event event, boolean marcar) {

		Iterator<ActionHandler> itActions = actions.iterator();		
		ActionHandler action;

		while (itActions.hasNext()){
			action = itActions.next();
                        if(marcar)
                            action.notifyEvent(event);
                        else action.notifyCancelEvent(event);
		}		
	}

	@Override
	public void registerEvent(ActionCommand cmd, Event e)  throws registerEventException {
		
                EnsureEventIsNotNull(e);
                EnsureCommandIsNotNull(cmd);

		ActionHandler action = ActionHandler.createActionSingle (cmd, e);
		this.actions.add(action);

	}

	private void validate(ActionCommand cmd, List<Event> e) throws registerEventException{
 		if(e== null || e.isEmpty())
			throw new registerEventException("La lista de eventos esta vacia");

		EnsureCommandIsNotNull(cmd);
	}


	@Override
	public void registerEventsContinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws registerEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupContinousWithNoCancellations  (cmd, e);
		this.actions.add(action);
	}

	@Override
	public void registerEventsDiscontinuousWithCancellations(ActionCommand cmd, List<Event> e) throws registerEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupDiscontinuousWithCancellations  (cmd, e);
		this.actions.add(action);
	}

	@Override
	public void registerEventsDiscontinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws registerEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupDiscontinuousWithNoCancellations  (cmd, e);
		this.actions.add(action);
	}

	@Override
	public void registerEventsWithOrderContinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws registerEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupOrderContinousWithNoCancellations  (cmd, e);
		this.actions.add(action);
	}

	@Override
	public void registerEventsWithOrderDiscontinuousWithCancellations(ActionCommand cmd, List<Event> e) throws registerEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupOrderDiscontinuousWithCancellations  (cmd, e);
		this.actions.add(action);
	}

	@Override
	public void registerEventsWithOrderDiscontinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws registerEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupOrderDiscontinuousWithNoCancellations  (cmd, e);
		this.actions.add(action);
	}

	@Override
	public void registerCancellables(Event event1, Event event2) throws registerEventException {
                EnsureEventIsNotNull(event1);
                EnsureEventIsNotNull(event2);
 
		EventCancel eventCancel = new EventCancel (event1, event2);
		this.eventsCancel.add(eventCancel);
	}

	@Override
	public void reset() {
		actions.clear();
		eventsCancel.clear();
	}

}
