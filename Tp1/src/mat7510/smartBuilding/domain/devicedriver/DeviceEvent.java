package mat7510.smartBuilding.domain.devicedriver;

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
public abstract class DeviceEvent implements Event {

	/**
	 * Todo evento debe proveer su nombre, para 
	 * que el Usuario pueda identificarlo en la GUI
	 * y asi configurar la interaccion entre Devices
	 * 
	 * @return el nombre del evento, tal como se le debe mostrar al Usuario final
	 */
	private String eventName;
	
	/**
	 * El Device al que corresponde este evento
	 */
	private DeviceDriver deviceDriver;


	public DeviceEvent(DeviceDriver deviceDriver, String eventName) {
		if (deviceDriver == null)
			throw new IllegalArgumentException("Cannot instantiate DeviceEvent with null DeviceDriver");
		if (eventName == null)
			throw new IllegalArgumentException("Cannot instantiate DeviceEvent with null eventName");
		if (eventName.trim().equalsIgnoreCase(""))
			throw new IllegalArgumentException("Cannot instantiate DeviceEvent with blank eventName");
		
		this.deviceDriver = deviceDriver;
		this.eventName = eventName;
	}
	

	public String getEventName() {
		return eventName;
	}


	public DeviceDriver getDeviceDriver() {
		return deviceDriver;
	}

	@Override
	public String toString() {
		return "DeviceEvent <" + eventName + "> for DeviceDriver <" + deviceDriver + ">";
	}

	
	@Override
	/**
	 * La comparacion entre un DeviceEvent y un Event generico de API
	 * No puede sobreescribirse en los que implementen los Drivers!
	 * 
	 * Esta comparacion se transmite a la comparacion
	 * DeviceEvent vs DeviceEvent la cual 
	 * tiene una implementacion default aca, 
	 * pero SI puede ser sobreescrita
	 */
	public final boolean equals(Event anotherEvent) {
		if (anotherEvent instanceof DeviceEvent)
			return equals((DeviceEvent)anotherEvent);
		return false;
	}

	/**
	 * Dos eventos seran iguales si y solo si:
	 * El DeviceDriver es el mismo
	 * El nombre de Evento es el mismo (ignore case)
	 * 
	 * @param anotherEvent
	 * @return
	 */
	public boolean equals(DeviceEvent anotherEvent) {
		
		if (! this.deviceDriver.equals(anotherEvent.deviceDriver))
			return false;
		if (! this.eventName.trim().equalsIgnoreCase(anotherEvent.eventName.trim()))
			return false;
		return true;
	}

}
