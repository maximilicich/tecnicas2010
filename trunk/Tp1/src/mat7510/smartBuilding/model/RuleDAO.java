package mat7510.smartBuilding.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mat7510.xml.DOMUtils;
import mat7510.xml.XmlException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @author MA_Xx
 *
 */
public class RuleDAO {

	private static RuleDAO instance = new RuleDAO();
	private RuleDAO() {
	}
	
	private static final String XML_FILENAME = "res/ruleConfig.xml";
	
	/**
	 * LOS TAGS XML
	 */
	private static final String RULES_SECTION_TAG = "smartBuildingRules";
	private static final String RULE_ELEMENT_TAG = "rule";
	
	private static final String RULE_ATTR_ORDERED = "ordered";
	private static final String RULE_ATTR_CONTINUOUS = "continuous";
	private static final String RULE_ATTR_ENABLED = "enabled";
	
	private static final String RULE_ID_TAG = "ruleID";
	private static final String RULE_DESCRIPTION_TAG = "ruleDescription";
	
	private static final String DEVICEDRIVER_ID_TAG = "deviceDriverID";
	
	private static final String RULE_ACTION_ELEMENT_TAG = "action";
	private static final String RULE_ACTION_NAME_TAG = "actionName";
	
	private static final String RULE_EVENTS_SECTION_TAG = "events";
	private static final String RULE_EVENT_ELEMENT_TAG = "event";
	private static final String RULE_EVENT_NAME_TAG = "eventName";

	
	/**
	 * DeviceDriverLoader Es un Singleton
	 *  
	 * @return la instancia Singleton de la Clase
	 */
	public static RuleDAO getInstance() {
		return instance;
	}

	
	/**
	 * 
	 * @return El conjunto de reglas configuradas. Set vacio si no hay reglas configuradas
	 * @throws SmartBuildingException
	 */
	public Set<Rule> getRules() throws SmartBuildingException {
		
		InputStream xml = this.getClass().getResourceAsStream(XML_FILENAME);
		
		Document domXml = null;
		
		try {
			domXml = DOMUtils.getInstance().getDocument(xml);
			
		} catch (Exception e) {
			throw new SmartBuildingException("Error al obtener archivo de Configuración de Reglas " + XML_FILENAME, e);
		}
		
		Element rulesSection = getRulesSection(domXml);
	
		List<Element> ruleElements = getRuleElements(rulesSection);
		// Podria no haber rules...no importa, devolvemos set vacio, entonces...
		
		Set<Rule> rules = new LinkedHashSet<Rule>();
		
		for (Element element : ruleElements) {
			String ruleID = getUniqueAttributeValue(element, RULE_ID_TAG);
			String ruleDescription = getUniqueAttributeValue(element, RULE_DESCRIPTION_TAG);
			
			DeviceAction deviceAction = getDeviceAction(element);
			
			List<DeviceEvent> events = getDeviceEvents(element);
			
			// Construimos la REGLA:
			Rule rule = new Rule.Builder(ruleID, ruleDescription).action(deviceAction).build();
			for (DeviceEvent deviceEvent : events) {
				rule.addDeviceEvent(deviceEvent);
			}
			
			if (! rules.add(rule))
				throw new SmartBuildingException("Rule Duplicated ! (ID : " + ruleID + ", description : " + ruleDescription + ")");
		}
		
		return rules;
		
	}


	private List<DeviceEvent> getDeviceEvents(Element element) throws SmartBuildingException {
		
		Element eventsSection;
		
		try {
			eventsSection = DOMUtils.getInstance().getUniqueElementByName(element, RULE_EVENTS_SECTION_TAG);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve Section " + RULE_EVENTS_SECTION_TAG + " from XML", e);
		}
		
		List<Element> eventElements; 
		
		try {
			eventElements = DOMUtils.getInstance().getElementsByName(eventsSection, RULE_EVENT_ELEMENT_TAG);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve Section " + RULE_EVENT_ELEMENT_TAG + " from XML", e);
		}
		
		List<DeviceEvent> events = new ArrayList<DeviceEvent>();
		
		for (Element eventElement : eventElements) {
			String deviceDriverID = getUniqueAttributeValue(eventElement, DEVICEDRIVER_ID_TAG);
			String eventName = getUniqueAttributeValue(eventElement, RULE_EVENT_NAME_TAG);

			DeviceDriver dev = DeviceDriverDAO.getInstance().getDeviceDriverByID(deviceDriverID);
			if (dev == null)
				throw new SmartBuildingException("Error trying to retrieve DeviceDriver for ID " + deviceDriverID + " (event name <" + eventName + ">) : DeviceDriver NOT FOUND.");

			DeviceEvent event = dev.getDeviceEventByName(eventName);
			if (event == null)
				throw new SmartBuildingException("Error trying to retrieve DeviceEvent for Device ID " + deviceDriverID + " (event name <" + eventName + ">) : EVENT NOT FOUND.");
			
			events.add(event);

		}
		
		return events;
	}


	private DeviceAction getDeviceAction(Element element) throws SmartBuildingException {
		
		Element actionSection;
		
		try {
			actionSection = DOMUtils.getInstance().getUniqueElementByName(element, RULE_ACTION_ELEMENT_TAG);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve Section " + RULE_ACTION_ELEMENT_TAG + " from XML", e);
		}
		
		String deviceDriverID = getUniqueAttributeValue(actionSection, DEVICEDRIVER_ID_TAG);
		String actionName = getUniqueAttributeValue(actionSection, RULE_ACTION_NAME_TAG);

		DeviceDriver dev = DeviceDriverDAO.getInstance().getDeviceDriverByID(deviceDriverID);
		if (dev == null)
			throw new SmartBuildingException("Error trying to retrieve DeviceDriver for ID " + deviceDriverID + " (action name <" + actionName + ">) : DeviceDriver NOT FOUND.");
		
		DeviceAction action = dev.getDeviceActionByName(actionName);
		if (action == null)
			throw new SmartBuildingException("Error trying to retrieve DeviceAction for Device ID " + deviceDriverID + " (action name <" + actionName + ">) : Action NOT FOUND.");
		
		return action;
	}


	private List<Element> getRuleElements(Element root) throws SmartBuildingException {
		try {
			return DOMUtils.getInstance().getElementsByName(root, RULE_ELEMENT_TAG);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve Section " + RULE_ELEMENT_TAG + " from XML", e);
		}
	}


	private Element getRulesSection(Document domXml) throws SmartBuildingException {
		try {
			return DOMUtils.getInstance().getUniqueElementByName(domXml, RULES_SECTION_TAG);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve Section " + RULES_SECTION_TAG + " from XML", e);
		}
	}

	
	private String getUniqueAttributeValue(Element element, String attribute) throws SmartBuildingException {
		try {
			return DOMUtils.getInstance().getUniqueAttributeValue(element, attribute);
		} catch (XmlException e) {
			throw new SmartBuildingException("Error trying to retrieve value for element " + attribute + " from XML", e);
		}
		
	}

}
