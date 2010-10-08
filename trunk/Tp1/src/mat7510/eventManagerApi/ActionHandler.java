package mat7510.eventManagerApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActionHandler {

	private ActionCommand command;

	private List<Event> events;

	private ArrayList<Boolean> eventsIndexs;

        private boolean ContinousWith;

        private boolean acceptCancellables;

	int amountActivated;

	private boolean order;


	static public ActionHandler createActionSingleWithCancellations ( ActionCommand command, Event event){
		return new ActionHandler(command ,event,true);
	}

	static public ActionHandler createActionSingleWithNoCancellations ( ActionCommand command, Event event){
		return new ActionHandler(command ,event,false);
	}

	static public ActionHandler createActionGroupContinousWithCancellations( ActionCommand command, List<Event> event){
		return new ActionHandler(command ,event, false,true,true);
	}

	static public ActionHandler createActionGroupContinousWithNoCancellations( ActionCommand command, List<Event> event){
		return new ActionHandler(command ,event, false,true,false);
	}

        static public ActionHandler createActionGroupDiscontinuousWithCancellations( ActionCommand command, List<Event> event){
		return new ActionHandler(command ,event, false,false,true);
	}

	static public ActionHandler createActionGroupDiscontinuousWithNoCancellations( ActionCommand command, List<Event> event){
		return new ActionHandler(command ,event, false,false,false);
	}

        static public ActionHandler createActionGroupOrderContinousWithCancellations( ActionCommand command, List<Event> event){
		return new ActionHandler(command ,event, true,true,true);
	}

	static public ActionHandler createActionGroupOrderContinousWithNoCancellations( ActionCommand command, List<Event> event){
		return new ActionHandler(command ,event, true,true,false);
	}

        static public ActionHandler createActionGroupOrderDiscontinuousWithCancellations( ActionCommand command, List<Event> event){
		return new ActionHandler(command ,event, true,false,true);
	}

	static public ActionHandler createActionGroupOrderDiscontinuousWithNoCancellations( ActionCommand command, List<Event> event){
		return new ActionHandler(command ,event, true,false,false);
	}


	private void createEvents() {
		// Inicializa el array de eventos de control del comando
		amountActivated=0;
		eventsIndexs = new ArrayList<Boolean>();
		for (int index = 0; index < events.size(); index++ ){
			eventsIndexs.add(index,false);
		}
	}

	private ActionHandler ( ActionCommand command, Event event, boolean acceptCancellables){

		this.command = command;
		events = new ArrayList<Event>();
		eventsIndexs = new ArrayList<Boolean>();
		this.events.add(event);
		setOrder(false);
		createEvents();
		amountActivated=0;
                this.acceptCancellables=acceptCancellables;
                this.ContinousWith=false;
	}

	private ActionHandler ( ActionCommand command, List<Event> events, boolean order,boolean ContinousWith, boolean acceptCancellables){

		this.command = command;
		this.events = events;
		setOrder(order);
		createEvents();
		amountActivated=0;
                this.acceptCancellables=acceptCancellables;
                this.ContinousWith=ContinousWith;
	}	    

	private void clearEvents() {		
		// Inicializa el array de eventos de control del comando
		if(amountActivated==0)
			return;

		amountActivated=0;
		for (int index = 0; index < events.size(); index++ ){	
			eventsIndexs.set(index,false);
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

	public boolean getOrder() {
		return order;
	}

	public void activateEvent (int index){
		// Cuando se activa se setea en el array como activo(true)
		if(eventsIndexs.get(index)!=true){
			if ( order == true ){
				if(verifyOrder( index ) == true){
					eventsIndexs.set(index, true);
					amountActivated++;
				}
			}else{
				eventsIndexs.set(index, true);
				amountActivated++;
			}
		}
	}

	public boolean isActivedEvent(int index){
		return eventsIndexs.get(index);
	}

	private void cancelEventsWithOrder (int index){

		for( int i=index ; i < eventsIndexs.size() ; i++){
			if(eventsIndexs.get(i) == true){
				eventsIndexs.set(i,false);
				amountActivated--;
			}
		}
	}

	public void cancelEvent (int index){
		// Cuando se cancela seteo el estado a desactivado

		if (eventsIndexs.get(index) == true){
			if(order)
				cancelEventsWithOrder (index);
			else{
				eventsIndexs.set(index,false);
				amountActivated--;
			}
		}	
	}

	private boolean verifyOrder(int index) {
		// Verifica que no haya ningun evento con indice menor al que se esta revisando dentro de la lista	
		int i=0;

		if(amountActivated==index-1){
			while(i<index){
				if(eventsIndexs.get(i)==false)
					return false;
				i++;
			}
		}
		return true;
	}

	public boolean isActive() {
		if(amountActivated==eventsIndexs.size())
			return true;
		else return false;
	}

	public void cleanState(){
		// Vuelve los eventos al momento inicial	
		clearEvents();
	}

        public boolean isContinuos(){
            return ContinousWith;
        }

        public boolean acceptCancellables(){
            return acceptCancellables;
        }

        public void notifyEvent(Event event,boolean marcar){
            int index=0;
            boolean changeState=false;
            Event actionEvent;
            Iterator<Event> itEvents = getEventIterator();

            //Si no acepto cancelables  y voy a cancelar (marcar==false) entonces no entro
            if (!(!acceptCancellables() && marcar==false)){
                  while (itEvents.hasNext()){
                       actionEvent = itEvents.next();
                       if (event.equals(actionEvent) && marcar == true){
                           if(!isActivedEvent(index)){
                              // marca el evento
                              activateEvent(index);
                              changeState=true;

                              if (isActive()){
                                  getCommand().execute();
                                  cleanState();
                              }
                              //Se recorre toda la lista para verificar si registran dos veces el mismo evento
                              if(getOrder()==true)
                                 break;
                           }
                        }

                        if (event.equals(actionEvent) && marcar == false){
                            // Se desactivan todos los eventos que cumplan
                             if(isContinuos())
                                cleanState();
                             else cancelEvent(index);
                        }
                            index ++;
                 }

                  //Si es continuo y no hubo cambios entonces se rompio la continuidad
                 if(isContinuos() && !changeState)
                      cleanState();
           }else{
                   // No acepto cancelables y voy a cancelar -> si es continuo se  rompe la continuidad
                  if(isContinuos())
                     cleanState();
           }
        }

}