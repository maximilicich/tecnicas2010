package mat7510.smartBuilding.model;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mat7510.xml.DOMUtils;
import mat7510.xml.XmlException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Clase Singleton que se encarga de leer el archivo XML de configuracion
 * de los Drivers de Dispositivos e instanciar para cada uno la Clase
 * Correspondiente.
 * 
 * @author Grupo 10
 *
 */
public class DeviceDriverDAO {

	private static DeviceDriverDAO instance = new DeviceDriverDAO();
	private DeviceDriverDAO() {
	}
	
	private static final String XML_FILENAME = "res/deviceDriverConfig.xml";
	
	/**
	 * LOS TAGS XML
	 */
	private static final String DEVICE_DRIVERS_SECTION_TAG = "deviceDrivers";
	private static final String DEVICE_DRIVER_ELEMENT_TAG = "deviceDriver";
	private static final String DEVICE_DRIVER_ELEMENT_ID_TAG = "deviceID";
	private static final String DEVICE_DRIVER_ELEMENT_NAME_TAG = "deviceDescription";
	private static final String DEVICE_DRIVER_ELEMENT_CLASS_TAG = "driverClass";
	
	/**
	 * DeviceDriverLoader Es un Singleton
	 *  
	 * @return la instancia Singleton de la Clase
	 */
	public static DeviceDriverDAO getInstance() {
		return instance;
	}

	/**
	 * Este metodo devuelve la lista de DeviceDrivers configurados para SmartBuilding
	 * instanciando por Reflection a cada Driver segun la clase configurada para c/u 
	 * 
	 * @return Un Set de DeviceDrivers
	 *  
	 * @throws SmartBuildingException Excepcion general 
	 */
	public Set<DeviceDriver> getDeviceDrivers() throws SmartBuildingException {
		
		InputStream xml = this.getClass().getResourceAsStream(XML_FILENAME);
		
		Document domXml = null;
		
		try {
			domXml = DOMUtils.getInstance().getDocument(xml);
			// DOMUtils.getInstance().printDomToXml(domXml, System.out);
			
		} catch (Exception e) {
			throw new SmartBuildingException("Error al obtener archivo de Configuraci�n de Drivers " + XML_FILENAME, e);
		}
		
		Element devDriversSection = getDeviceDriversSection(domXml);
	
		List<Element> devDriverElements = getDeviceDriverElements(devDriversSection);
		
		if (devDriverElements.isEmpty()) 
			throw new SmartBuildingException("No existen Drivers de Dispositivos configurados en el XML");
	
		Set<DeviceDriver> deviceDrivers = new LinkedHashSet<DeviceDriver>();
		
		for (Element element : devDriverElements) {
			String deviceID = getDeviceID(element);
			String deviceDescription = getDeviceDescription(element);
			String deviceDriverClass = getDeviceDriverClass(element);
			DeviceDriver devDriver = createDeviceDriver(deviceID, deviceDescription, deviceDriverClass);
			if (! deviceDrivers.add(devDriver))
				throw new SmartBuildingException("No se pudo agregar en el conjunto de DeviceDrivers instanciados al Device ID : " + deviceID + ", descripcion : " + deviceDescription);
		}
		
		return deviceDrivers;
		
	}

	public DeviceDriver getDeviceDriverByID(String id) throws SmartBuildingException {
		if (id == null)
			throw new IllegalArgumentException("DeviceDriver ID must not be null");
		if (id.trim().equalsIgnoreCase(""))
			throw new IllegalArgumentException("DeviceDriver ID must not be blank");
		
		Set<DeviceDriver> devices = getDeviceDrivers();
		for (DeviceDriver deviceDriver : devices) {
			if (deviceDriver.getDeviceID().equalsIgnoreCase(id))
				return deviceDriver;
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @param domXml
	 * @return
	 * @throws SmartBuildingException
	 */
	private Element getDeviceDriversSection(Document domXml) throws SmartBuildingException {
	
		List<Element> devDriversSection = null;
		
		try {
			devDriversSection = 
				DOMUtils.getInstance().getElementsByName(domXml, DEVICE_DRIVERS_SECTION_TAG);
		} catch (Exception e) {
			throw new SmartBuildingException(e);
		}
		
		if (devDriversSection.size() == 0) {
			throw new SmartBuildingException("No existe la secci�n " + DEVICE_DRIVERS_SECTION_TAG + " en el XML");
		}
		if (devDriversSection.size() > 1) {
			throw new SmartBuildingException("Existe mas de una secci�n " + DEVICE_DRIVERS_SECTION_TAG + " en el XML");
		}
		
		return devDriversSection.iterator().next();
	
	}

	/**
	 * 
	 * @param root
	 * @return
	 * @throws SmartBuildingException
	 */
	private List<Element> getDeviceDriverElements(Element root) throws SmartBuildingException {
		try {
			List<Element> drivers = DOMUtils.getInstance().getElementsByName(root, DEVICE_DRIVER_ELEMENT_TAG);
			return drivers;
		} catch (XmlException e) {
			throw new SmartBuildingException(e);
		}
	}
	

	/**
	 * 
	 * @param element
	 * @return
	 * @throws SmartBuildingException 
	 */
	private String getDeviceDescription(Element devDriverElement) throws SmartBuildingException {

		try {
			return DOMUtils.getInstance().getUniqueAttributeValue(devDriverElement, DEVICE_DRIVER_ELEMENT_NAME_TAG);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve value for element " + DEVICE_DRIVER_ELEMENT_NAME_TAG + " from XML", e);
		}
	
	}

	/**
	 * 
	 * @param element
	 * @return
	 * @throws SmartBuildingException 
	 */
	private String getDeviceID(Element devDriverElement) throws SmartBuildingException {

		try {
			return DOMUtils.getInstance().getUniqueAttributeValue(devDriverElement, DEVICE_DRIVER_ELEMENT_ID_TAG);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve value for element " + DEVICE_DRIVER_ELEMENT_ID_TAG + " from XML", e);
		}

	}

	/**
	 * 
	 * @param element
	 * @return
	 * @throws SmartBuildingException 
	 */
	private String getDeviceDriverClass(Element devDriverElement) throws SmartBuildingException {

		try {
			return DOMUtils.getInstance().getUniqueAttributeValue(devDriverElement, DEVICE_DRIVER_ELEMENT_CLASS_TAG);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve value for element " + DEVICE_DRIVER_ELEMENT_CLASS_TAG + " from XML", e);
		}

	}

	
	/**
	 * 
	 * @param element
	 * @return
	 * @throws SmartBuildingException 
	 */
	private DeviceDriver createDeviceDriver(String deviceID, 
											String deviceDescription,
											String deviceDriverClass) throws SmartBuildingException {

		DeviceDriver devDriver;
		
		// CREAMOS EL DRIVER POR REFLECTION !!
		try {
			Class<?> cls = Class.forName(deviceDriverClass);
		    Class<?> partypes[] = new Class[2];
            partypes[0] = String.class;
            partypes[1] = String.class;
			Constructor<?> ct = cls.getConstructor(partypes);
			
			Object arglist[] = new Object[2];
			arglist[0] = deviceID;
			arglist[1] = deviceDescription;

			devDriver = (DeviceDriver)ct.newInstance(arglist);
		}
		catch (Throwable e) {
			throw new SmartBuildingException("Error al intentar instanciar el DeviceDriver por Reflection", e);
		}

		return devDriver;
		
	}
	
}