package mat7510.playground.xml.dom;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

@SuppressWarnings("serial")
public class DOMUtils implements java.io.Serializable {

	public DOMUtils() {
	}

	/**
	 * 
	 * fuentes:
	 * http://java.sun.com/developer/codesamples/xml.html
	 * http://java.sun.com/developer/technicalArticles/xml/WebAppDev2/
	 * ( originalmente: MyDOMParserBean )
	 * Hicimos algunas modificaciones igualmente...
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Document getDocument(InputStream file) throws Exception {

		// Step 1: create a DocumentBuilderFactory
		DocumentBuilderFactory dbf =
			DocumentBuilderFactory.newInstance();

		dbf. setIgnoringElementContentWhitespace(true);
		
		// Step 2: create a DocumentBuilder
		DocumentBuilder db = dbf.newDocumentBuilder();

		// Step 3: parse the input file to get a Document object
		// Document doc = db.parse(new File(file));
		Document doc = db.parse(file);
		return doc;
	}      

	
	/**
	 * Using JAXP in implementation independent manner create a document object
	 * using which we create a xml tree in memory
	 * 
	 * Tomado directamente de:
	 * http://www.totheriver.com/learn/xml/xmltutorial.html
	 * 
	 * @throws ParserConfigurationException 
	 */
	public static Document createDocument() throws ParserConfigurationException {

		//get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		//get an instance of builder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//create an instance of DOM
		return db.newDocument();

	}

	
}
