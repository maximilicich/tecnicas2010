package mat7510.smartBuilding.dao.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mat7510.smartBuilding.dao.DeviceDriverDAO;
import mat7510.smartBuilding.domain.devicedriver.DeviceDriver;
import mat7510.smartBuilding.exception.SmartBuildingException;
import mat7510.smartBuilding.utils.WorkingDirectory;
import mat7510.xml.DOMUtils;
import mat7510.xml.XmlException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Clase que se encarga de leer el archivo XML de configuracion
 * de los Drivers de Dispositivos e instanciar para cada uno la Clase
 * Correspondiente.
 * 
 * @author Grupo 10
 *
 */
public class DeviceDriverDAOXMLImplementation implements DeviceDriverDAO  {

	private static final String XML_FILENAME = "/res/deviceDriverConfig.xml";

	private static Set<DeviceDriver> devicePool = null;
	
	/**
	 * LOS TAGS XML
	 */
	private static final String DEVICE_DRIVERS_SECTION_TAG = "deviceDrivers";
	private static final String DEVICE_DRIVER_ELEMENT_TAG = "deviceDriver";
	private static final String DEVICE_DRIVER_ELEMENT_ID_TAG = "deviceID";
	private static final String DEVICE_DRIVER_ELEMENT_NAME_TAG = "deviceDescription";
	private static final String DEVICE_DRIVER_ELEMENT_CLASS_TAG = "driverClass";

	/**
	 * 
	 */
	public DeviceDriverDAOXMLImplementation() {
	}
	

	/**
	 * Este metodo devuelve la lista de DeviceDrivers configurados para SmartBuilding
	 * instanciando por Reflection a cada Driver segun la clase configurada para c/u 
	 * 
	 * @return Un Set de DeviceDrivers
	 *  
	 * @throws SmartBuildingException Excepcion general 
	 */
	@SuppressWarnings("static-access")
	public Set<DeviceDriver> getDeviceDrivers() throws SmartBuildingException {
	
		if (devicePool != null) {
			return devicePool;
		}
		else {
			
			Document domXml = createDomFromFile();
			
			Element devDriversSection = getDeviceDriversSection(domXml);
		
			List<Element> devDriverElements = getDeviceDriverElements(devDriversSection);
			
			if (devDriverElements.isEmpty()) 
				throw new SmartBuildingException("No existen Drivers de Dispositivos configurados en el XML");
		
			Set<DeviceDriver> deviceDrivers = new LinkedHashSet<DeviceDriver>();
			
			for (Element element : devDriverElements) {
				String deviceID = getUniqueAttributeValue(element, DEVICE_DRIVER_ELEMENT_ID_TAG);
				String deviceDescription = getUniqueAttributeValue(element, DEVICE_DRIVER_ELEMENT_NAME_TAG);
				String deviceDriverClass = getUniqueAttributeValue(element, DEVICE_DRIVER_ELEMENT_CLASS_TAG);
				DeviceDriver devDriver = createDeviceDriver(deviceID, deviceDescription, deviceDriverClass);
				if (! deviceDrivers.add(devDriver))
					throw new SmartBuildingException("ITEM DUPLICADO: No se pudo agregar en el conjunto de DeviceDrivers instanciados al Device ID : " + deviceID + ", descripcion : " + deviceDescription);
			}
			
			this.devicePool = deviceDrivers;
			
			return deviceDrivers;

		}
		
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

	
	/**
	 * 
	 * Vuelca todas las Rules en el Repositorio
	 * Reemplaza todas las existentes por este conjunto
	 * 
	 * La unicidad de ID esta asegurada por set un SET
	 * (y el equals de las Rules asegura la igualdad por IDs
	 * 
	 * @param rules
	 * @throws SmartBuildingException 
	 */
	@SuppressWarnings("static-access")
	public void setDeviceDrivers(Set<DeviceDriver> deviceDrivers) throws SmartBuildingException {
		
		Document dom;
		try {
			dom = DOMUtils.getInstance().createNewDocument();
		} catch (XmlException e) {
			throw new SmartBuildingException("Error at creating new empty DOM Document", e);
		}

		//create the root element 
		Element rootElement = dom.createElement(DEVICE_DRIVERS_SECTION_TAG);
		dom.appendChild(rootElement);

		for (DeviceDriver deviceDriver : deviceDrivers) {
			rootElement.appendChild(createDeviceDriverElement(dom, deviceDriver));
		}
		
		try {
			// DOMUtils.getInstance().printDomToXml(dom, System.out);
			File file = WorkingDirectory.get();
			String path = file.getAbsolutePath();
			path = path + XML_FILENAME;
			DOMUtils.getInstance().printDomToXml(dom, new FileOutputStream(path));
		} catch (XmlException e) {
			throw new SmartBuildingException(e);
		} catch (FileNotFoundException e) {
			throw new SmartBuildingException(e);
		} 
		
		this.devicePool = deviceDrivers;
	}

	
	private Node createDeviceDriverElement(Document dom, DeviceDriver deviceDriver) {
		
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
		
		return deviceDriverElement;
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

		devDriversSection.appendChild(createDeviceDriverElement(dom, deviceDriver));
		
		File file = WorkingDirectory.get();
		String path = file.getAbsolutePath();
		path = path + XML_FILENAME;
		
		try {
			// DOMUtils.getInstance().printDomToXml(dom, System.out);
			DOMUtils.getInstance().printDomToXml(dom, new FileOutputStream(path));//XML_FILENAME));
		} catch (XmlException e) {
			throw new SmartBuildingException(e);
		} catch (FileNotFoundException e) {
			throw new SmartBuildingException(e);
		} 

		// AGREAMOS EL DEVICE AL POOL:
		if (! devicePool.add(deviceDriver)) {
		//	throw new SmartBuildingException("DUPLICATED ITEM: Error trying to add new Device Driver ID " + deviceDriver.getDeviceID() + " already exists");
		}

	}
	
	
	/**
	 * 
	 * @param domXml
	 * @return
	 * @throws SmartBuildingException
	 */
	private Element getDeviceDriversSection(Document domXml) throws SmartBuildingException {
		try {
			return DOMUtils.getInstance().getUniqueElementByName(domXml, DEVICE_DRIVERS_SECTION_TAG);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve element " + DEVICE_DRIVERS_SECTION_TAG + " from XML", e);
		}
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
	 * @param attribute
	 * @return
	 * @throws SmartBuildingException
	 */
	private String getUniqueAttributeValue(Element element, String attribute) throws SmartBuildingException {
		try {
			return DOMUtils.getInstance().getUniqueAttributeValue(element, attribute);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve value for element " + attribute + " from XML", e);
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
			throw new SmartBuildingException("Error al intentar instanciar el DeviceDriver por Reflection (class: " + deviceDriverClass + ")", e);
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

			
			 //InputStream xml = this.getClass().getResourceAsStream(XML_FILENAME);
		     //return DOMUtils.getInstance().getDocument(xml);
			//File file = new File (XML_FILENAME);
			File file = WorkingDirectory.get();
			String path = file.getAbsolutePath();
			path = path + XML_FILENAME;
			return DOMUtils.getInstance().getDocument(new FileInputStream (path));//file.getAbsolutePath()));//new FileInputStream(XML_FILENAME));
			
		} catch (Exception e) {
			throw new SmartBuildingException("Error al obtener archivo de Configuración de Drivers " + XML_FILENAME, e);
		}

	}
	
}
