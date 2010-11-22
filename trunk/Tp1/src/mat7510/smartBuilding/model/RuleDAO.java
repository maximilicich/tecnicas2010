package mat7510.smartBuilding.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mat7510.xml.DOMUtils;
import mat7510.xml.XmlException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * 
 * @author MA_Xx
 *
 */
public class RuleDAO {

	private static RuleDAO instance = new RuleDAO();
	private RuleDAO() {
	}
	
	private static final String XML_FILENAME = "/res/ruleConfig.xml";
	
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
	 * @param rules
	 */
	public void setRules(Set<Rule> rules) {
		
		
		
	}

	
	/**
	 * 
	 * @return El conjunto de reglas configuradas. Set vacio si no hay reglas configuradas
	 * @throws SmartBuildingException
	 */
	public Set<Rule> getRules() throws SmartBuildingException {
		
		Document domXml = createDomFromFile();
		
		Element rulesSection = getRulesSection(domXml);
	
		List<Element> ruleElements = getRuleElements(rulesSection);
		// Podria no haber rules...no importa, devolvemos set vacio, entonces...
		
		Set<Rule> rules = new LinkedHashSet<Rule>();
		
		for (Element element : ruleElements) {
			
			Boolean isEnabled = DOMUtils.getInstance().getBooleanValue(element.getAttribute(RULE_ATTR_ENABLED), true);
			Boolean isContinuous = DOMUtils.getInstance().getBooleanValue(element.getAttribute(RULE_ATTR_CONTINUOUS), false);
			Boolean isOrdered = DOMUtils.getInstance().getBooleanValue(element.getAttribute(RULE_ATTR_ORDERED), false);
			
			String ruleID = getUniqueAttributeValue(element, RULE_ID_TAG);
			String ruleDescription = getUniqueAttributeValue(element, RULE_DESCRIPTION_TAG);
			
			DeviceAction deviceAction = getDeviceAction(element);
			
			List<DeviceEvent> events = getDeviceEvents(element);
			
			// Construimos la REGLA:
			Rule rule = new Rule.Builder(ruleID, ruleDescription).
									action(deviceAction).
									continuous(isContinuous).
									ordered(isOrdered).
									build();
			
			rule.setEnabled(isEnabled);
			
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
	
	
	public void addRule(Rule rule) throws SmartBuildingException {
		
		if (rule == null) 
			throw new IllegalArgumentException("Cannot add a null Rule");

		if (rule.getRuleID() == null)
			throw new IllegalArgumentException("Cannot add a Rule with null ID");

		if (rule.getRuleID().trim().equalsIgnoreCase(""))
			throw new IllegalArgumentException("Cannot add a Rule with blank ID");

		
		Document dom = createDomFromFile();
		Element rulesSection = getRulesSection(dom);
		
		Element ruleElement = dom.createElement(RULE_ELEMENT_TAG);
		ruleElement.setAttribute(RULE_ATTR_ENABLED, DOMUtils.getInstance().getBooleanRepresentation(rule.isEnabled()));
		ruleElement.setAttribute(RULE_ATTR_CONTINUOUS, DOMUtils.getInstance().getBooleanRepresentation(rule.isContinuous()));
		ruleElement.setAttribute(RULE_ATTR_ORDERED, DOMUtils.getInstance().getBooleanRepresentation(rule.isOrdered()));
	
		Element ruleID = dom.createElement(RULE_ID_TAG);
		Text id = dom.createTextNode(rule.getRuleID());
		ruleID.appendChild(id);

		Element ruleDescription = dom.createElement(RULE_DESCRIPTION_TAG);
		Text description = dom.createTextNode(rule.getRuleDescription());
		ruleDescription.appendChild(description);

		ruleElement.appendChild(ruleID);
		ruleElement.appendChild(ruleDescription);

		Element actionElement = dom.createElement(RULE_ACTION_ELEMENT_TAG);
		if (rule.getDeviceAction() != null) {
			
			Element deviceDriverIDElement = dom.createElement(DEVICEDRIVER_ID_TAG);
			String deviceDriverID = rule.getDeviceAction().getDeviceDriver().getDeviceID();
			deviceDriverIDElement.appendChild(dom.createTextNode(deviceDriverID));
			actionElement.appendChild(deviceDriverIDElement);
			
			Element actionNameElement = dom.createElement(RULE_ACTION_NAME_TAG);
			String actionName = rule.getDeviceAction().getActionName();
			actionNameElement.appendChild(dom.createTextNode(actionName));
			actionElement.appendChild(actionNameElement);
		}
		
		ruleElement.appendChild(actionElement);

		Element eventsSectionElement = dom.createElement(RULE_EVENTS_SECTION_TAG);
		
		for (DeviceEvent deviceEvent : rule.getDeviceEvents()) {
			Element eventElement = dom.createElement(RULE_EVENT_ELEMENT_TAG);

			Element deviceDriverIDElement = dom.createElement(DEVICEDRIVER_ID_TAG);
			String deviceDriverID = deviceEvent.getDeviceDriver().getDeviceID();
			deviceDriverIDElement.appendChild(dom.createTextNode(deviceDriverID));
			eventElement.appendChild(deviceDriverIDElement);
			
			Element eventNameElement = dom.createElement(RULE_EVENT_NAME_TAG);
			String eventName = deviceEvent.getEventName();
			eventNameElement.appendChild(dom.createTextNode(eventName));
			eventElement.appendChild(eventNameElement);
			
			eventsSectionElement.appendChild(eventElement);

		}
		
		ruleElement.appendChild(eventsSectionElement);
		
		rulesSection.appendChild(ruleElement);

		
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
			// return DOMUtils.getInstance().getDocument(new FileInputStream(XML_FILENAME));
			//File file = new File(XML_FILENAME);
			File file = WorkingDirectory.get();
			String path = file.getAbsolutePath();
			path = path + XML_FILENAME;
			return DOMUtils.getInstance().getDocument(new FileInputStream(path));//file.getAbsolutePath()));//XML_FILENAME));
			
		} catch (Exception e) {
			throw new SmartBuildingException("Error al obtener archivo de Configuración de Reglas " + XML_FILENAME, e);
		}
	}

	
}
