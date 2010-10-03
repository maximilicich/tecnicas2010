package mat7510.eventManagerApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActionHandler {
	
	private ActionCommand command;
	
	private List<Event> events;

        //TODO: creo q este estado deberia estar en la interfaz Event
        private ArrayList<Boolean> eventsIndexs;

        int amountActivated;

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

       private void createEvents() {
	// Inicializa el array de eventos de control del comando
		amountActivated=0;
                eventsIndexs = new ArrayList<Boolean>();
		for (int index = 0; index < events.size(); index++ ){
                        eventsIndexs.add(index,false);
		}
	}
       
	private ActionHandler ( ActionCommand command, Event event){
		
		this.command = command;
		this.events.add(event);
		setOrder(false);
		createEvents();
                amountActivated=0;
                //TODO:crear el array
	}
		
	private ActionHandler ( ActionCommand command, List<Event> events, boolean order){
		
		this.command = command;
		this.events = events;
		setOrder(order);
		createEvents();
                amountActivated=0;
                //TODO:crear el array
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

        private void cancelEventsWithOrder (int index){

            for( int i=index ; i < eventsIndexs.size() ; i++){
                if(eventsIndexs.get(index) == true){
                    eventsIndexs.set(index,false);
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

}
