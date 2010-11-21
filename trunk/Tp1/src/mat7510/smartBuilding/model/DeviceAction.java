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
 * La implementacion del DeviceDriver debe asegurar la unicidad
 * de los nombres, ya que en el Framework BAS (SmartBuilding)
 * las acciones se identifican por su nombre 
 *  
 * @author Grupo 10
 *
 */
public abstract class DeviceAction implements ActionCommand {

	/**
	 * El Device al que corresponde esta accion
	 */
	private DeviceDriver deviceDriver;

	/**
	 * Toda Accion debe proveer su nombre, para 
	 * que el Usuario pueda identificarla en la GUI
	 * y asi configurar la interaccion entre Devices
	 * 
	 * El Driver debera asegurar que este nombre sea unico entre
	 * las acciones disponibles, para identificarla univocamente
	 * 
	 */
	private String actionName;
	
	public DeviceAction(DeviceDriver deviceDriver, String actionName) {
		if (deviceDriver == null)
			throw new IllegalArgumentException("Cannot instantiate DeviceAction with null DeviceDriver");
		this.deviceDriver = deviceDriver;
		this.actionName = actionName;
	}

	public DeviceDriver getDeviceDriver() {
		return deviceDriver;
	}

	public String getActionName() {
		return actionName;
	}
	
	@Override
	public String toString() {
		return "DeviceAction <" + actionName + "> for DeviceDriver <" + deviceDriver + ">";
	}
	
}
