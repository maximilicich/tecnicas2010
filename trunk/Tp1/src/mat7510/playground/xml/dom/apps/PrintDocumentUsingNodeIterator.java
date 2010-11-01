package mat7510.playground.xml.dom.apps;

import java.io.FileNotFoundException;
import java.io.InputStream;

import mat7510.playground.xml.dom.BlanksAndNewLinesNodeFilter;
import mat7510.playground.xml.dom.DOMTraversal;
import mat7510.playground.xml.dom.DOMUtils;
import mat7510.playground.xml.dom.PrinterNodeProcessor;

import org.w3c.dom.Document;


/**
 * Aplicacion para imprimir un doc XML con
 * DOM, usando NodeIterator como DocumentTraversal
 * 
 * @see
 * http://www.ibm.com/developerworks/xml/library/x-tipdomnf.html
 * 
 * @author MA_Xx
 *
 */
public class PrintDocumentUsingNodeIterator {
	
	public static void main(String[] args) {

		PrintDocumentUsingNodeIterator app = new PrintDocumentUsingNodeIterator();

		InputStream xmlFile = null;

		if (args.length > 0) {
			try {
				xmlFile = XMLProvider.INSTANCE.getXMLFile(args[0]);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {
			// un xml default
			xmlFile = XMLProvider.INSTANCE.getXMLResource("note.xml");
		}
		
		try {
			app.printDocumentUsingNodeIterator(xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 
	 * @param xmlFile
	 * @throws Exception
	 */
	public void printDocumentUsingNodeIterator(InputStream xmlFile) throws Exception {

		Document document = DOMUtils.getDocument(xmlFile);

		System.out.println("traversing doc using NodeIterator...");

		DOMTraversal.traverseDocument(document, 
									  new PrinterNodeProcessor(), 
									  new BlanksAndNewLinesNodeFilter());
		
	}

}
