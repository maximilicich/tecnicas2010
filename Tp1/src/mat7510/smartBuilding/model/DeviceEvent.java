package mat7510.smartBuilding.model;

import mat7510.eventManagerApi.version2.Event;

/**
 * Representa un Evento informado por un dispositivo al ambiente
 * Este evento extiende el concepto de Evento segun la API eventManager
 * 
 * En SmartBuilding la interaccion entre dispositivos se configura
 * relacionando Acciones con Eventos
 * Las Acciones deben ser DeviceActions
 * Los Eventos deben ser DeviceEventS
 * 
 * @author Grupo 10
 *
 */
public interface DeviceEvent extends Event {

	/**
	 * Todo evento debe proveer su nombre, para 
	 * que el Usuario pueda identificarlo en la GUI
	 * y asi configurar la interaccion entre Devices
	 * 
	 * @return el nombre del evento, tal como se le debe mostrar al Usuario final
	 */
	public String getEventName();
	
}
