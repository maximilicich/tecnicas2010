package mat7510.smartBuilding.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mat7510.xml.DOMUtils;
import mat7510.xml.XmlException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

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
		
		Document domXml = createDomFromFile();
		
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


	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SmartBuildingException
	 */
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

	
	public void addDeviceDriver(DeviceDriver deviceDriver) throws SmartBuildingException {

		if (deviceDriver == null) 
			throw new IllegalArgumentException("Cannot add a null DeviceDriver");
		
		if (deviceDriver.getDeviceID() == null)
			throw new IllegalArgumentException("Cannot add a DeviceDriver with null ID");

		if (deviceDriver.getDeviceID().trim().equalsIgnoreCase(""))
			throw new IllegalArgumentException("Cannot add a DeviceDriver with blank ID");

		
		Document dom = createDomFromFile();

		Element devDriversSection = getDeviceDriversSection(dom);
		
		Element deviceDriverElement = dom.createElement(DEVICE_DRIVER_ELEMENT_TAG);

		Element deviceDriverID = dom.createElement(DEVICE_DRIVER_ELEMENT_ID_TAG);
		Text id = dom.createTextNode(deviceDriver.getDeviceID());
		deviceDriverID.appendChild(id);

		Element deviceDriverDescription = dom.createElement(DEVICE_DRIVER_ELEMENT_NAME_TAG);
		Text description = dom.createTextNode(deviceDriver.getDeviceDescription());
		deviceDriverDescription.appendChild(description);

		Element deviceDriverClass = dom.createElement(DEVICE_DRIVER_ELEMENT_CLASS_TAG);
		Text driverClass = dom.createTextNode(deviceDriver.getClass().getName());
		deviceDriverClass.appendChild(driverClass);

		deviceDriverElement.appendChild(deviceDriverID);
		deviceDriverElement.appendChild(deviceDriverDescription);
		deviceDriverElement.appendChild(deviceDriverClass);
		
		devDriversSection.appendChild(deviceDriverElement);
		
		
		try {
			// DOMUtils.getInstance().printDomToXml(dom, System.out);
			DOMUtils.getInstance().printDomToXml(dom, new FileOutputStream(XML_FILENAME));
		} catch (XmlException e) {
			throw new SmartBuildingException(e);
		} catch (FileNotFoundException e) {
			throw new SmartBuildingException(e);
		} 

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
			throw new SmartBuildingException("No existe la sección " + DEVICE_DRIVERS_SECTION_TAG + " en el XML");
		}
		if (devDriversSection.size() > 1) {
			throw new SmartBuildingException("Existe mas de una sección " + DEVICE_DRIVERS_SECTION_TAG + " en el XML");
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
	
	
	/**
	 * 
	 * @return
	 * @throws SmartBuildingException
	 */
	private Document createDomFromFile() throws SmartBuildingException {
		
		try {
			// ATENCION: ANTES USABAMOS getResourceAsStream() para 
			// que la ruta relativa sea "relativa" a esta clase
			// pero esto nos complica cuando queremos grabar en el archivo
			// con el OutputStream...
			// Asi que lo hacemos relativo a res por afuera de mat7510
			// (lo anterior queda comentado)

			// InputStream xml = this.getClass().getResourceAsStream(XML_FILENAME);
			// return DOMUtils.getInstance().getDocument(xml);
			return DOMUtils.getInstance().getDocument(new FileInputStream(XML_FILENAME));
			
		} catch (Exception e) {
			throw new SmartBuildingException("Error al obtener archivo de Configuración de Drivers " + XML_FILENAME, e);
		}

	}
	
}
