package mat7510.smartBuilding.model.devicedriver;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


// import mat7510.eventManagerApi.version2.EventListener;

/**
 * 
 * Un Driver de dispositivo (Device Driver)
 * generico para interactuar con el smartBuilding BAS (Bulding Automation System)
 * 
 * Este Driver generico interactua con la API controladora de Eventos (eventManagerAPI)
 * 
 * Un Driver representa en el BAS a un único Dispositivo
 * Por tanto el Driver debe ser creado a partir del ID y description 
 * del Dispositivo (Device) al cual representa.
 * 
 * Todo driver de dispositivo debe proveeer 
 * a) Las {@link mat7510.smartBuilding.model.devicedriver.DeviceAction acciones } que pueden realizarse sobre el dispositivo
 * b) Los {@link mat7510.smartBuilding.model.devicedriver.DeviceEvent eventos } que el dispositivo informa al entorno
 * c) El estado actual del dispositivo: El estado se define por un conjunto 
 * 	  de nombres de atributos y su valor actual correspondiente 
 * 	  Estos valores son siempre de tipo String
 * 
 *  El Driver provee un modo para suscribir uno o mas {@link EventListener mat7510.eventManagerApi.version2.EventListener } 
 *  Los cuales recibiran los Eventos emitidos por el dispositivo
 *  
 *  El modo de ejecutar una accion sobre el dispositivo es:
 *  1. identificar la Accion a ejecutar en la lista devuelta por getActions() 
 *  2. ejecutar el objeto accion (deviceAction.execute())
 *  
 * @author Grupo 10 
 *
 */
public abstract class DeviceDriver {

	/**
	 * El ID del Device representado por este Driver
	 * Este ID identificara univocamente al Device en el BAS
	 * El BAS asegura que no se instancien dos Drivers con el mismo ID de Device
	 * (sea cual fuere el tipo de Device).
	 * 
	 * Este ID es inherente al ambiente del BAS
	 * (no es inherente a la implementacion de cada Driver)
	 * El BAS, por configuracion, indicara el ID para cada DeviceDriver
	 */
	private String deviceID;
	
	/**
	 * La descripcion del Device representado por este Driver
	 * Esta descripcion es inherente al ambiente del BAS
	 * (no es inherente a la implementacion de cada Driver)
	 * El Sistema por configuracion podra dar una descripcion a cada DeviceDriver
	 * para ser interpretada por el Usuario
	 */
	private String deviceDescription;
	
	/**
	 * 
	 */
	private Set<DeviceEventListener> eventListeners = new LinkedHashSet<DeviceEventListener>();
	
	/**
	 * Instanciar un DeviceDriver requiere OBLIGATORIAMENTE
	 * especificar el ID y descripcion del Device representado
	 * 
	 * Estos ID y descripcion no podran ser modificados una vez creada la instancia
	 * 
	 * @param deviceID
	 * @param deviceDescription
	 */
	public DeviceDriver(String deviceID, String deviceDescription) {
		
		if (deviceID == null || deviceID.trim().equalsIgnoreCase(""))
			throw new IllegalArgumentException("Device ID must not be null");
		this.deviceID = deviceID;
		this.deviceDescription = deviceDescription;
		
	}
	
	/**
	 * El ID de Device: Identifica univocamente al Device
	 * asociado a esta instancia de Driver
	 * (puede ser una MAC Address)

	 * @return un String que representa el ID
	 */
	public String getDeviceID() {
		return this.deviceID;
	}

	
	/**
	 * Una descripcion del Device asociado a esta instancia de Driver
	 * 
	 * @return la descripcion
	 */
	public String getDeviceDescription() {
		return this.deviceDescription;
	}
	
	
	/**
	 * Las {@link mat7510.smartBuilding.model.devicedriver.DeviceAction acciones } que pueden realizarse sobre el dispositivo
	 * 
	 * @return
	 */
	public abstract List<DeviceAction> getActions();
	
	/**
	 * Los {@link mat7510.smartBuilding.model.devicedriver.DeviceEvent eventos } que el dispositivo informa al entorno
	 * 
	 * @return
	 */
	public abstract List<DeviceEvent> getEvents();
	
	/**
	 * El estado actual del dispositivo: El estado se define por un conjunto 
	 *  de nombres de atributos y su valor actual correspondiente 
	 *  Estos valores son siempre de tipo String
	 *  
	 * @return un Mapa de pares ordenados (nombre, valor)
	 * en donde cada par ordenado corresponde a un atributo del dispositivo
	 * 
	 */
	public abstract Map<String, String> getState();
	
	
	
	/**
	 *  Metodo para suscribir uno o mas {@link EventListener mat7510.smartBuilding.model.DeviceEventListener } 
	 *  Los cuales deberan recibir los Eventos emitidos por el dispositivo
	 *  
	 * @param eventListener
	 */
	public void addEventListener(DeviceEventListener eventListener) {
		this.eventListeners.add(eventListener);
	}
	
	
	/**
	 * Metodo para obtener el conjunto de EventListeners suscriptos.
	 * 
	 * @return el Conjunto de EventListeners suscriptos. Si no hubiera, debe devolverse el Conjunto Vacio. NO null 
	 */
	public Set<DeviceEventListener> getEventListeners() {
		return this.eventListeners;
	}
	
	/**
	 * El Driver debe proveer el modo de Obtener una accion propia
	 * a partir de su nombre.
	 * El nombre identifica univocamente a una Accion, en el contexto
	 * de un DeviceDriver
	 * 
	 * Si no se encuentra, se devuelve null
	 * 
	 * @param deviceActionName el nombre de la accion
	 * @return la accion
	 */
	public abstract DeviceAction getDeviceActionByName(String deviceActionName);

	
	/**
	 * El Driver debe proveer el modo de Obtener un Evento propio
	 * a partir de su nombre.
	 * El nombre identifica univocamente a un Evento, en el contexto
	 * de un DeviceDriver
	 * 
	 * Si no se encuentra, se devuelve null
	 * 
	 * @param deviceActionName el nombre de la accion
	 * @return la accion
	 */
	public abstract DeviceEvent getDeviceEventByName(String deviceEventName);
	
	
	@Override
	/**
	 * Un DeviceDriver es igual a otro si y solo si sus ID son iguales
	 */
	public boolean equals(Object obj) {
		
		if (!( obj instanceof DeviceDriver))
			return false;
		
		DeviceDriver anotherDevice = (DeviceDriver)obj;
		
		if (this.deviceID == null || anotherDevice.deviceID == null)
			return false;
		
		return this.deviceID.trim().equalsIgnoreCase(anotherDevice.deviceID.trim());
	}

	@Override
	public int hashCode() {
		return this.deviceID.hashCode();
	}
	
	@Override
	public String toString() {
		return "DeviceDriver ID : " + deviceID + " | description : " + deviceDescription; 
	}
	
}
