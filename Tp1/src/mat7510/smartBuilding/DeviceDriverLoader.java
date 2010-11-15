package mat7510.smartBuilding;

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
public class DeviceDriverLoader {

	private static DeviceDriverLoader instance = new DeviceDriverLoader();
	private DeviceDriverLoader() {
	}
	
	/**
	 * LOS TAGS XML
	 * Son Publicos para que el Usuario pueda consultar y 
	 * entender la estructura del XML de Configuracion
	 */
	public static final String DEVICE_DRIVERS_SECTION_TAG = "deviceDrivers";
	public static final String DEVICE_DRIVER_ELEMENT_TAG = "deviceDriver";
	public static final String DEVICE_DRIVER_ELEMENT_ID_TAG = "deviceID";
	public static final String DEVICE_DRIVER_ELEMENT_NAME_TAG = "deviceDescription";
	public static final String DEVICE_DRIVER_ELEMENT_CLASS_TAG = "driverClass";
	
	/**
	 * DeviceDriverLoader Es un Singleton
	 *  
	 * @return la instancia Singleton de la Clase
	 */
	public static DeviceDriverLoader getInstance() {
		return instance;
	}

	/**
	 * Este metodo recibe como unico parametro el InputStream
	 * correspondiente al XML de Configuracion de los DeviceDrivers
	 * Realiza el parseo del mismo (determinando si esta bien conformado)
	 * E instancia porf Reflection a cada Driver segun la clase configurada en el XML 
	 * 
	 * La estructura del XML debe ser
	 * <DEVICE_DRIVERS_SECTION_TAG>
	 * 		<DEVICE_DRIVER_ELEMENT_TAG>
	 * 				<DEVICE_DRIVER_ELEMENT_NAME_TAG>
	 * 					nombre (identificador) del device driver
	 * 				</DEVICE_DRIVER_ELEMENT_NAME_TAG>
	 * 				<DEVICE_DRIVER_ELEMENT_CLASS_TAG>
	 * 					fully-qualified name de la Clase del Device Driver
	 * 				</DEVICE_DRIVER_ELEMENT_CLASS_TAG>
	 * 		</DEVICE_DRIVER_ELEMENT_TAG>
	 * </DEVICE_DRIVERS_SECTION_TAG>
	 * 
	 * El valor de cada TAG puede consultarse como atributo estatico de esta clase
	 * 
	 * @param xml El XML de configuracion
	 * 
	 * @return Un Map cuya key es el nombre del Driver definido en el XML
	 * Y el valor asociado es la instancia del Driver correspondiente.
	 *  
	 * @throws SmartBuildingException Excepcion general 
	 */
	public Set<DeviceDriver> getDeviceDrivers(InputStream xml) throws SmartBuildingException {
		
		Document domXml = null;
		
		try {
			domXml = DOMUtils.getInstance().getDocument(xml);
			// DOMUtils.getInstance().printDomToXml(domXml, System.out);
			
		} catch (Exception e) {
			throw new SmartBuildingException(e);
		}
		
		Element devDriversSection = getDeviceDriversSection(domXml);
	
		List<Element> devDriverElements = getDeviceDriverElements(devDriversSection);
		
		if (devDriverElements.isEmpty()) 
			throw new SmartBuildingException("No existen Drivers de Dispositivos configurados en el XML");
	
		Set<DeviceDriver> deviceDrivers = new LinkedHashSet<DeviceDriver>();
		
		for (Element element : devDriverElements) {
			String deviceID = getDeviceID(element);
			String deviceDescription = getDeviceDescription(element);
			DeviceDriver devDriver = createDeviceDriver(element, deviceID, deviceDescription);
			if (! deviceDrivers.add(devDriver))
				throw new SmartBuildingException("No se pudo agregar en el conjunto de DeviceDrivers instanciados al Device ID : " + deviceID + ", descripcion : " + deviceDescription);
		}
		
		return deviceDrivers;
		
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

		return getDeviceDriverAttributeValue(devDriverElement, DEVICE_DRIVER_ELEMENT_NAME_TAG); 		
	
	}

	/**
	 * 
	 * @param element
	 * @return
	 * @throws SmartBuildingException 
	 */
	private String getDeviceID(Element devDriverElement) throws SmartBuildingException {

		return getDeviceDriverAttributeValue(devDriverElement, DEVICE_DRIVER_ELEMENT_ID_TAG);
		
	}

	
	/**
	 * 
	 * @param element
	 * @return
	 * @throws SmartBuildingException 
	 */
	private String getDeviceDriverAttributeValue(Element devDriverElement, String attribute) throws SmartBuildingException {

		List<Element> attrElements = null;
		
		try {
			attrElements = 
				DOMUtils.getInstance().getElementsByName(devDriverElement, attribute);
		} catch (Exception e) {
			throw new SmartBuildingException(e);
		}
		
		if (attrElements.size() == 0) {
			throw new SmartBuildingException("No existe la sección " + attribute + " en el XML");
		}
		if (attrElements.size() > 1) {
			throw new SmartBuildingException("Existe mas de una sección " + attribute + " en el XML");
		}
		
		return attrElements.iterator().next().getTextContent();
		
	}

	
	
	/**
	 * 
	 * @param element
	 * @return
	 * @throws SmartBuildingException 
	 */
	private DeviceDriver createDeviceDriver(Element devDriverElement, 
											String deviceID, 
											String deviceDescription) throws SmartBuildingException {

		List<Element> classElements = null;
		
		DeviceDriver devDriver = null;
		
		try {
			classElements = 
				DOMUtils.getInstance().getElementsByName(devDriverElement, DEVICE_DRIVER_ELEMENT_CLASS_TAG);
		} catch (Exception e) {
			throw new SmartBuildingException(e);
		}
		
		if (classElements.size() == 0) {
			throw new SmartBuildingException("No existe la sección " + DEVICE_DRIVER_ELEMENT_CLASS_TAG + " en el XML");
		}
		if (classElements.size() > 1) {
			throw new SmartBuildingException("Existe mas de una sección " + DEVICE_DRIVER_ELEMENT_CLASS_TAG + " en el XML");
		}
		
		// CREAMOS EL DRIVER POR REFLECTION !!
		try {
			Class<?> cls = Class.forName(classElements.iterator().next().getTextContent());
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
