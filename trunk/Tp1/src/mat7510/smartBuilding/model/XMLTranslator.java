package mat7510.smartBuilding.model;

import java.io.InputStream;
import java.lang.reflect.Constructor;

import mat7510.smartBuilding.model.devicedriver.DeviceDriver;
import mat7510.xml.DOMUtils;
import mat7510.xml.XmlException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLTranslator {

	/**
	 * TAGS XML para DeviceDriver
	 */
	private static final String DEVICE_DRIVER_ELEMENT_TAG = "deviceDriver";
	private static final String DEVICE_DRIVER_ELEMENT_ID_TAG = "deviceID";
	private static final String DEVICE_DRIVER_ELEMENT_NAME_TAG = "deviceDescription";
	private static final String DEVICE_DRIVER_ELEMENT_CLASS_TAG = "driverClass";

	/**
	 * 
	 * @param xmlInput
	 * @return
	 * @throws SmartBuildingException
	 */
	public DeviceDriver getDeviceDriver(InputStream xmlInput) throws SmartBuildingException {
		
		Document domXml;
		try {
			domXml = DOMUtils.getInstance().getDocument(xmlInput);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to get document from inputstream", e);
		}
		
		Element devDriverElement = getUniqueElementByName(domXml, DEVICE_DRIVER_ELEMENT_TAG);
	
		String deviceID = getUniqueAttributeValue(devDriverElement, DEVICE_DRIVER_ELEMENT_ID_TAG);
		String deviceDescription = getUniqueAttributeValue(devDriverElement, DEVICE_DRIVER_ELEMENT_NAME_TAG);
		String deviceDriverClass = getUniqueAttributeValue(devDriverElement, DEVICE_DRIVER_ELEMENT_CLASS_TAG);
		DeviceDriver deviceDriver = createDeviceDriver(deviceID, deviceDescription, deviceDriverClass);
		
		return deviceDriver;
		
	}
	
	public Rule getRule(InputStream xmlInput) {
		// TODO FALTA IMPLEMENTAR
		return null;
	}
	
	
	private Element getUniqueElementByName(Node node, String elementName) throws SmartBuildingException {
		try {
			return DOMUtils.getInstance().getUniqueElementByName(node, elementName);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve element " + elementName + " from XML", e);
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
			throw new SmartBuildingException("Error trying to instantiate DeviceDriver by Reflection (class: " + deviceDriverClass + ")", e);
		}

		return devDriver;
		
	}
	
}
