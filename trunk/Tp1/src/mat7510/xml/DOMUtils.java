package mat7510.xml;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

@SuppressWarnings("serial")
/**
 * Clase Singleton que brinda servicios varios para creacion y parseo de DOM XML
 * 
 *  @author Grupo 10
 */
public class DOMUtils implements java.io.Serializable {

	private static DOMUtils instance = new DOMUtils();

	private DOMUtils() {
	}

	/**
	 * Es un Singleton
	 * @return
	 */
	public static DOMUtils getInstance() {                
		return instance;
	}


	/**
	 * Encapsula los pasos para 
	 * Parsear un Archivo XML y devolver el DOM
	 * 
	 * fuentes:
	 * http://java.sun.com/developer/codesamples/xml.html
	 * http://java.sun.com/developer/technicalArticles/xml/WebAppDev2/
	 * ( originalmente: MyDOMParserBean )
	 * Hicimos algunas modificaciones igualmente...
	 * 
	 * @param xmlFile
	 * @return
	 * 
	 * @throws XmlException
	 */
	public Document getDocument(InputStream xml) throws XmlException  {
       		try {
			// Step 1: create a DocumentBuilderFactory
			DocumentBuilderFactory dbf =
				DocumentBuilderFactory.newInstance();

			dbf. setIgnoringElementContentWhitespace(true);

			// Step 2: create a DocumentBuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// Step 3: parse the input file to get a Document object
			// Document doc = db.parse(new File(file));
			Document doc = db.parse(xml);
			return doc;
		} catch (FileNotFoundException e) {
			throw new XmlException(e);
		} catch (ParserConfigurationException e) {
			throw new XmlException(e);
		} catch (SAXException e) {
			throw new XmlException(e);
		} catch (IOException e) {
			throw new XmlException(e);
		}

	}      


	/**
	 * Using JAXP in implementation independent manner create a document object
	 * using which we create a xml tree in memory
	 * 
	 * Tomado directamente de:
	 * http://www.totheriver.com/learn/xml/xmltutorial.html
	 * 
	 * @throws XmlException 
	 */
	public Document createNewDocument() throws XmlException {

		try {
			//get an instance of factory
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			//get an instance of builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//create an instance of DOM
			return db.newDocument();
		} catch (ParserConfigurationException e) {
			throw new XmlException(e);
		}

	}

	
	/**
	 * 
	 * @param node
	 * @param elementName
	 * @return
	 * @throws Exception
	 */
	public List<Element> getElementsByName(Node node, String elementName) throws XmlException {

		List<Element> elementList = new ArrayList<Element>();	

		NodeRetriever nodeRetriever = new NodeRetriever();

		DOMTraversal traverser = new DOMTraversal(nodeRetriever, new ElementByNameFilter(elementName));

		traverser.traverseNode(node);

		for (Node element : nodeRetriever) {
			elementList.add((Element) element);
		}

		return elementList;

	}

	
	/**
	 * 
	 * @param node
	 * @param elementName
	 * @return
	 * @throws XmlException
	 */
	public Element getUniqueElementByName(Node node, String elementName) throws XmlException {
		
		List<Element> elements = getElementsByName(node, elementName);
		
		if (elements.size() == 0) {
			throw new XmlException("Section " + elementName + " does not exist in XML");
		}
		if (elements.size() > 1) {
			throw new XmlException("Section " + elementName + " is not unique in XML");
		}
		
		return elements.iterator().next();
		
	}

	/**
	 * 
	 * @param element
	 * @param attribute
	 * @return
	 * @throws XmlException 
	 * 
	 */
	public String getUniqueAttributeValue(Element element, String attribute) throws XmlException {

		List<Element> attrElements = this.getElementsByName(element, attribute);
		
		if (attrElements.size() == 0) {
			throw new XmlException("Section " + attribute + " does not exist in XML");
		}
		if (attrElements.size() > 1) {
			throw new XmlException("Section " + attribute + " is not unique in XML");
		}
		
		return attrElements.iterator().next().getTextContent();
		
	}

	
	/**
	 * 
	 * @param dom
	 * @param out
	 * @throws TransformerException
	 */
	public void printDomToXml(Node dom, OutputStream out) throws XmlException {

		try {
			// Use a Transformer for output
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();

			DOMSource source = new DOMSource(dom);
			// StreamResult result = new StreamResult(System.out);

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");

			StreamResult result = new StreamResult(out);

			transformer.transform(source, result);

		} catch (TransformerException e) {
			throw new XmlException(e);
		}
	}
	
	
	/**
	 * En XML todo es string.
	 * Con este metodo obtenemos la representacion en string de un valor booleano
	 * @param boolValue
	 * @return
	 */
	public String getBooleanRepresentation(Boolean boolValue) {
		return (boolValue == null ? "no" : boolValue ? "yes" : "no" );
	}
	
	/**
	 * Convierte la representacion en String del valor booleano
	 * al valor boolean en si.
	 * En caso de que la representacion no corresponda a un valor valido,
	 * se devuelve el defaultvalue
	 * 
	 * @param booleanRepresentation
	 * @param defaultValue
	 * @return
	 */
	public Boolean getBooleanValue(String booleanRepresentation, Boolean defaultValue) {
		if (booleanRepresentation == null)
			return defaultValue;
		if (booleanRepresentation.trim().equalsIgnoreCase("yes"))
			return true;
		if (booleanRepresentation.trim().equalsIgnoreCase("no"))
			return false;
		return defaultValue;
		
	}
	

}
