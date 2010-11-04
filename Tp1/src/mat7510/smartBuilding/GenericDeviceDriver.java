package mat7510.smartBuilding;

import java.util.List;

import mat7510.eventManagerApi.version2.ActionCommand;
import mat7510.eventManagerApi.version2.Event;
import mat7510.eventManagerApi.version2.EventListener;

/**
 * 
 * Un Driver de dispositivo (Device Driver)
 * generico para interactuar con el smartBuilding BAS (Bulding Automation System)
 * 
 * Este Driver generico interactua con la API controladora de Eventos (eventManagerAPI)
 * 
 * Todo driver de dispositivo debe proveeer 
 * a) Las {@link mat7510.eventManagerApi.version2.ActionCommand acciones } que pueden realizarse sobre el dispositivo
 * b) Los {@link mat7510.eventManagerApi.version2.Event eventos } que el dispositivo informa al entorno
 * c) Los estados que posee el dispositivo (estados?)
 * d) Un modo de informar el valor actual de cada estado
 * 
 *  Asimismo, debe asegurar un modo para suscribir uno o mas {@link EventListener mat7510.eventManagerApi.version2.EventListener } 
 *  Los cuales deberan recibir los Eventos emitidos por el dispositivo
 *  
 *  El modo de ejecutar una accion sobre el dispositivo es:
 *  1. obtener con getActionCommand() el objeto {@link mat7510.eventManagerApi.version2.ActionCommand Accion } a partir del nombre de la accion
 *  2. ejecutar el objeto accion (actionCommand.execute())
 *  
 * @author Grupo 10 
 *
 */
public interface GenericDeviceDriver {

	/**
	 * 
	 * @return
	 */
	List<String> getActions();
	
	/**
	 * 
	 * @return
	 */
	List<String> getEvents();
	
	/**
	 * 
	 * @return
	 */
	List<String> getStates();
	
	/**
	 * 
	 * @param actionName
	 * @return
	 */
	ActionCommand getAction(String actionName);
	
	/**
	 * 
	 * @param eventName
	 * @return
	 */
	Event getEvent(String eventName);
	
	/**
	 * 
	 * @param state
	 * @return
	 */
	String getStateValue(String state);
	
	/**
	 * 
	 * @param eventListener
	 */
	void addEventListener(EventListener eventListener);
	
	
}
