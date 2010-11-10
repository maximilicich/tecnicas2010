package mat7510.smartBuilding;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public static final String DEVICE_DRIVER_ELEMENT_NAME_TAG = "name";
	public static final String DEVICE_DRIVER_ELEMENT_CLASS_TAG = "class";
	
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
	public Map<String, DeviceDriver> getDeviceDrivers(InputStream xml) throws SmartBuildingException {
		
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
	
		Map<String, DeviceDriver> map = new LinkedHashMap<String, DeviceDriver>();
		
		for (Element element : devDriverElements) {
			String devDriverName = getDeviceDriverName(element);
			DeviceDriver devDriver = createDeviceDriver(element);
			map.put(devDriverName, devDriver);
		}
		
		return map;
		
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
	private String getDeviceDriverName(Element devDriverElement) throws SmartBuildingException {

		List<Element> nameElements = null;
		
		try {
			nameElements = 
				DOMUtils.getInstance().getElementsByName(devDriverElement, DEVICE_DRIVER_ELEMENT_NAME_TAG);
		} catch (Exception e) {
			throw new SmartBuildingException(e);
		}
		
		if (nameElements.size() == 0) {
			throw new SmartBuildingException("No existe la sección " + DEVICE_DRIVER_ELEMENT_NAME_TAG + " en el XML");
		}
		if (nameElements.size() > 1) {
			throw new SmartBuildingException("Existe mas de una sección " + DEVICE_DRIVER_ELEMENT_NAME_TAG + " en el XML");
		}
		
		return nameElements.iterator().next().getTextContent();
		
	}

	
	/**
	 * 
	 * @param element
	 * @return
	 * @throws SmartBuildingException 
	 */
	private DeviceDriver createDeviceDriver(Element devDriverElement) throws SmartBuildingException {

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
			Constructor<?> ct = cls.getConstructor();
			devDriver = (DeviceDriver)ct.newInstance();
		}
		catch (Throwable e) {
			throw new SmartBuildingException(e);
		}
		
		return devDriver;
		
	}
	
}
