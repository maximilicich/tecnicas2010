package mat7510.smartBuilding.model;

import mat7510.eventManagerApi.version2.ActionCommand;

/**
 * Representa una Accion posible a ejecutar por un Device (dispositivo)
 * Esta accion extiende el concepto de ActionCommand segun la API eventManager
 * 
 * En SmartBuilding la interaccion entre dispositivos se configura
 * relacionando Acciones con Eventos
 * Las Acciones deben ser DeviceActions
 * Los Eventos deben ser DeviceEventS
 *  
 * @author Grupo 10
 *
 */
public interface DeviceAction extends ActionCommand {

	/**
	 * Toda Accion debe proveer su nombre, para 
	 * que el Usuario pueda identificarla en la GUI
	 * y asi configurar la interaccion entre Devices
	 * 
	 * @return el nombre de la accion, tal como se le debe mostrar al Usuario final
	 */
	public String getActionName();
	
}
