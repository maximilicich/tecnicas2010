package mat7510.eventManagerApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcreteEventManager implements EventManager {

	private List<ActionHandler> actions;

	private List<EventCancel> eventsCancel;


	private void ensureEventIsNotNull(Event e)throws RegisterEventException{
		if(e==null)
			throw new RegisterEventException("Evento nulo");
	}

	private void ensureCommandIsNotNull(ActionCommand cmd)throws RegisterEventException{
		if(cmd==null)
			throw new RegisterEventException("Comando nulo");
	}

	/** Permite inicializar una instancia del ConcreteEventmanager
	 */
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

	/**Permite notificar la ocurrencia de un evento
	 * @param  e Evento que a de notificarse
	 */
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

	/**Permite registrar un evento y su comando asociado.
	 * @param  e Evento que se va a escuchar
	 * @param cmd Comando asociado al evento. Es la accion a ejecutar al ocurrir el evento.
	 * @exception RegisterEventException se lanza la excepcion si el comando o el evento son nulos
	 */
	@Override
	public void registerEvent(ActionCommand cmd, Event e)  throws RegisterEventException {

		ensureEventIsNotNull(e);
		ensureCommandIsNotNull(cmd);

		ActionHandler action = ActionHandler.createActionSingle (cmd, e);
		this.actions.add(action);

	}

	private void validate(ActionCommand cmd, List<Event> e) throws RegisterEventException{
		if(e== null || e.isEmpty())
			throw new RegisterEventException("La lista de eventos esta vacia");

		ensureCommandIsNotNull(cmd);
	}

	/**Permite registrar una lista de eventos continuos que deben darse para ejecutar el comando asociado. No permite ser cancelados por otros eventos.
	 * @param  e Lista de eventos que se van a escuchar
	 * @param cmd Comando asociado a la lista de eventos. Es la accion a ejecutar al ocurrir de forma continua todos los eventos de la lista.
	 * @exception RegisterEventException se lanza la excepcion si el comando o los eventos son nulos
	 */
	@Override
	public void registerEventsContinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupContinousWithNoCancellations  (cmd, e);
		this.actions.add(action);
	}

	/**Permite registrar una lista de eventos discontinuos que deben ocurrir para ejecutar el comando asociado. Permite ser cancelados por otros eventos.
	 * @param  e Lista de eventos que se van a escuchar
	 * @param cmd Comando asociado a la lista de eventos. Es la accion a ejecutar al ocurrir todos los eventos de la lista.
	 * @exception RegisterEventException se lanza la excepcion si el comando o los eventos son nulos
	 */
	@Override
	public void registerEventsDiscontinuousWithCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupDiscontinuousWithCancellations  (cmd, e);
		this.actions.add(action);
	}
	/**Permite registrar una lista de eventos discontinuos que deben ocurrir para ejecutar el comando asociado. No permite ser cancelados por otros eventos.
	 * @param  e Lista de eventos que se van a escuchar
	 * @param cmd Comando asociado a la lista de eventos. Es la accion a ejecutar al ocurrir todos los eventos de la lista.
	 * @exception RegisterEventException se lanza la excepcion si el comando o los eventos son nulos
	 */
	@Override
	public void registerEventsDiscontinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupDiscontinuousWithNoCancellations  (cmd, e);
		this.actions.add(action);
	}
	/**Permite registrar una lista de eventos continuos que deben ocurrir en order para ejecutar el comando asociado. No permite ser cancelados por otros eventos.
	 * @param  e Lista de eventos en orden que se van a escuchar
	 * @param cmd Comando asociado a la lista de eventos. Es la accion a ejecutar al ocurrir todos los eventos de la lista en el orden .
	 * @exception RegisterEventException se lanza la excepcion si el comando o los eventos son nulos
	 */
	@Override
	public void registerEventsWithOrderContinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupOrderContinousWithNoCancellations  (cmd, e);
		this.actions.add(action);
	}
	/**Permite registrar una lista de eventos discontinuos que deben ocurrir en order para ejecutar el comando asociado. Permite ser cancelados por otros eventos.
	 * @param  e Lista de eventos sin orden que se van a escuchar
	 * @param cmd Comando asociado a la lista de eventos. Es la accion a ejecutar al ocurrir todos los eventos de la lista.
	 * @exception RegisterEventException se lanza la excepcion si el comando o los eventos son nulos
	 */
	@Override
	public void registerEventsWithOrderDiscontinuousWithCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupOrderDiscontinuousWithCancellations  (cmd, e);
		this.actions.add(action);
	}
	/**Permite registrar una lista de eventos discontinuos que deben ocurrir en order para ejecutar el comando asociado. No permite ser cancelados por otros eventos.
	 * @param  e Lista de eventos sin orden que se van a escuchar
	 * @param cmd Comando asociado a la lista de eventos. Es la accion a ejecutar al ocurrir todos los eventos de la lista.
	 * @exception RegisterEventException se lanza la excepcion si el comando o los eventos son nulos
	 */
	@Override
	public void registerEventsWithOrderDiscontinuousWithNoCancellations(ActionCommand cmd, List<Event> e) throws RegisterEventException {
		validate(cmd,e);
		ActionHandler action = ActionHandler.createActionGroupOrderDiscontinuousWithNoCancellations  (cmd, e);
		this.actions.add(action);
	}
	/**Permite registrar eventos cancelables es decir que la ocurrencia de un evento cancele un evento que haya surgido anteriormente.
	 * @param  eventSource Evento que va a cancelar.
	 * @param eventToBeCancel Evento que es cancelado
	 * @exception RegisterEventException se lanza la excepcion si los eventos son nulos
	 */
	@Override
	public void registerCancellables(Event eventSource, Event eventToBeCancel) throws RegisterEventException {
		ensureEventIsNotNull(eventSource);
		ensureEventIsNotNull(eventToBeCancel);

		EventCancel eventCancel = new EventCancel (eventSource, eventToBeCancel);
		this.eventsCancel.add(eventCancel);
	}

	@Override
	public void reset() {
		actions.clear();
		eventsCancel.clear();
	}

}
