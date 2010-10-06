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
	
	
	public void eventOccurred(Event e) throws exceptionRegisterEvent {
		
		if(e==null)
              throw new exceptionRegisterEvent("Evento nulo");	
		
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

	private void notifyChange(Event event, boolean marcar) throws exceptionRegisterEvent {
		
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
                                    if(!action.isActivedEvent(index)){
					// marca el evento 
					action.activateEvent(index);
                                        
                                        if (action.isActive()){
                                            action.getCommand().execute();
                                            action.cleanState();
                                        }
                                        //Recorro toda la lista x si registran dos veces el mismo evento
                                        if(action.getOrder()==true)
                                            break;
                                    }
				}
				
				if (event.equals(actionEvent) && marcar == false){
                                	// voy a desactivar todos los eventos que cumplan
					action.cancelEvent(index);      
				}
				index ++;
			}			
		}		
	}

	@Override
	public void register(ActionCommand cmd, Event e)  throws exceptionRegisterEvent {
	        if(e==null)
                    throw new exceptionRegisterEvent("Evento nulo");

                if(cmd==null)
                    throw new exceptionRegisterEvent("Comando nulo");

		ActionHandler action = ActionHandler.createActionSingle (cmd, e);
		this.actions.add(action);

	}

	@Override
	public void register(ActionCommand cmd, List<Event> e)  throws exceptionRegisterEvent {

                if(e== null || e.isEmpty())
                    throw new exceptionRegisterEvent("La lista de eventos esta vacia");

                if(cmd==null)
                    throw new exceptionRegisterEvent("Comando nulo");

		ActionHandler action = ActionHandler.createActionGroup  (cmd, e);
		this.actions.add(action);

	}

	@Override
	public void registerWithOrder(ActionCommand cmd, List<Event> e)  throws exceptionRegisterEvent {

                if(e== null || e.isEmpty())
                    throw new exceptionRegisterEvent("La lista de eventos esta vacia");
                
                if(cmd==null)
                    throw new exceptionRegisterEvent("Comando nulo");

		ActionHandler action = ActionHandler.createActionGroupOrder  (cmd, e);
		this.actions.add(action);

	}

	@Override
	public void registerCancellables(Event event1, Event event2) throws exceptionRegisterEvent {

                //TODO: ver de lanzar exepciones o que hacer en estos casos
                if(event1==null || event2==null)
                    throw new exceptionRegisterEvent("Evento null");

                if(event1.equals(event2))
                    throw new exceptionRegisterEvent("Los eventos que se desean registrar son iguales");

		EventCancel eventCancel = new EventCancel (event1, event2);
		this.eventsCancel.add(eventCancel);
	}

	@Override
	public void reset() {
		// TODO Implementar el reset(). Deberia desregistrar todos los eventos-comandos y cancelables, dejando al manager en su estado inicial...
		actions.clear();
                eventsCancel.clear();
	}

}
