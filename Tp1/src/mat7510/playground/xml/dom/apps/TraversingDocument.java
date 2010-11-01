package mat7510.playground.xml.dom.apps;


import java.io.IOException;

import mat7510.playground.xml.dom.DOMUtils;
import mat7510.playground.xml.dom.PropertyPrinter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * 
 * @author MA_Xx
 *
 */
public class TraversingDocument {

	private static PropertyPrinter printer = new PropertyPrinter();

	public static void main(String[] args) {

		try {
			Document document = DOMUtils.getDocument(XMLProvider.INSTANCE.getXMLResource("note.xml"));

			Node rootNode = document.getDocumentElement();

			System.out.println("traversing doc...");
			writeTraversal(rootNode);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	// note use of recursion
	public static void writeTraversal(Node node) throws IOException {

		printer.writeNode(node);
		
		if (node.hasChildNodes()) {
			Node firstChild = node.getFirstChild();
			writeTraversal(firstChild);
		}
		Node nextNode = node.getNextSibling();
		if (nextNode != null) 
			writeTraversal(nextNode);

	}

	
}
