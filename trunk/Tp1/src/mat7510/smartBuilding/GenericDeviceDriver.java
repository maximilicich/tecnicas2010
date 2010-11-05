package mat7510.smartBuilding;

import java.util.List;
import java.util.Map;

import mat7510.eventManagerApi.version2.EventListener;

/**
 * 
 * Un Driver de dispositivo (Device Driver)
 * generico para interactuar con el smartBuilding BAS (Bulding Automation System)
 * 
 * Este Driver generico interactua con la API controladora de Eventos (eventManagerAPI)
 * 
 * Todo driver de dispositivo debe proveeer 
 * a) Las {@link mat7510.smartBuilding.DeviceAction acciones } que pueden realizarse sobre el dispositivo
 * b) Los {@link mat7510.smartBuilding.DeviceEvent eventos } que el dispositivo informa al entorno
 * c) El estado actual del dispositivo: El estado se define por un conjunto 
 * 	  de nombres de atributos y su valor actual correspondiente 
 * 	  Estos valores son siempre de tipo String
 * 
 *  Asimismo, debe asegurar un modo para suscribir uno o mas {@link EventListener mat7510.eventManagerApi.version2.EventListener } 
 *  Los cuales deberan recibir los Eventos emitidos por el dispositivo
 *  
 *  El modo de ejecutar una accion sobre el dispositivo es:
 *  1. identificar la Accion a ejecutar en la lista devuelta por getActions() 
 *  2. ejecutar el objeto accion (deviceAction.execute())
 *  
 * @author Grupo 10 
 *
 */
public interface GenericDeviceDriver {

	/**
	 * Las {@link mat7510.smartBuilding.DeviceAction acciones } que pueden realizarse sobre el dispositivo
	 * 
	 * @return
	 */
	List<DeviceAction> getActions();
	
	/**
	 * Los {@link mat7510.smartBuilding.DeviceEvent eventos } que el dispositivo informa al entorno
	 * 
	 * @return
	 */
	List<DeviceEvent> getEvents();
	
	/**
	 * El estado actual del dispositivo: El estado se define por un conjunto 
	 *  de nombres de atributos y su valor actual correspondiente 
	 *  Estos valores son siempre de tipo String
	 *  
	 * @return un Mapa de pares ordenados (nombre, valor)
	 * en donde cada par ordenado corresponde a un atributo del dispositivo
	 * 
	 */
	Map<String, String> getState();
	
	
	/**
	 *  Metodo para suscribir uno o mas {@link EventListener mat7510.eventManagerApi.version2.EventListener } 
	 *  Los cuales deberan recibir los Eventos emitidos por el dispositivo
	 *  
	 * @param eventListener
	 */
	void addEventListener(EventListener eventListener);
	
	
}
