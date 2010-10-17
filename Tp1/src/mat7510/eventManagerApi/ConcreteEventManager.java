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

		if(e==null)
			throw new NullPointerException("Evento nulo");

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
		ActionHandler action;

		while (itActions.hasNext()){
			action = itActions.next();	
			action.notifyEvent(event,marcar);
		}		
	}

	@Override
	public void registerEvent(ActionCommand cmd, Event e)  throws registerEventException {
		if(e==null)
			throw new registerEventException("Evento nulo");

		if(cmd==null)
			throw new registerEventException("Comando nulo");

		ActionHandler action = ActionHandler.createActionSingle (cmd, e);
		this.actions.add(action);

	}

	private void validate(ActionCommand cmd, List<Event> e) throws registerEventException{
		if(e== null || e.isEmpty())
			throw new registerEventException("La lista de eventos esta vacia");

		if(cmd==null)
			throw new registerEventException("Comando nulo");
	}

	@Override
	public void registerEventsContinuousWithCancellations(ActionCommand cmd, List<Event> e) throws registerEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupContinousWithCancellations  (cmd, e);
		this.actions.add(action);
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

		if(event1==null || event2==null)
			throw new registerEventException("Evento null");

		if(event1.equals(event2))
			throw new registerEventException("Los eventos que se desean registrar son iguales");

		EventCancel eventCancel = new EventCancel (event1, event2);
		this.eventsCancel.add(eventCancel);
	}

	@Override
	public void reset() {
		actions.clear();
		eventsCancel.clear();
	}

}
