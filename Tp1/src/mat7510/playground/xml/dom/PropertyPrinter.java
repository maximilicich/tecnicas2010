package mat7510.playground.xml.dom;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.w3c.dom.Node;


public class PropertyPrinter {

	private Writer out;

	public PropertyPrinter(Writer out) {
		if (out == null) {
			throw new NullPointerException("Writer must be non-null.");
		}
		this.out = out;
	}

	public PropertyPrinter() {
		this(new OutputStreamWriter(System.out));
	}

	private int nodeCount = 0;

	public void writeNode(Node node) throws IOException {

		if (node == null) {
			throw new NullPointerException("Node must be non-null.");
		}
		if (node.getNodeType() == Node.DOCUMENT_NODE 
				|| node.getNodeType() == Node.DOCUMENT_FRAGMENT_NODE) { 
			// starting a new document, reset the node count
			nodeCount = 1; 
		}
		// evitamos imprimir los textos en blank y newlines
		if (node.getNodeType() == Node.TEXT_NODE) {
			if (node.getTextContent().trim().equalsIgnoreCase(""))
				return;
		}

		String name      = node.getNodeName(); // never null
		String type      = NodeTyper.getTypeName(node); // never null
		String localName = node.getLocalName();
		String uri       = node.getNamespaceURI();
		String prefix    = node.getPrefix();
		String value     = node.getNodeValue();
		String textCont	 = node.getTextContent();

		StringBuffer result = new StringBuffer();
		result.append("Node " + nodeCount + ":\r\n");
		result.append("  Type: " + type + "\r\n");
		result.append("  Name: " + name + "\r\n");
		if (localName != null) {
			result.append("  Local Name: " + localName + "\r\n");
		}
		if (prefix != null) {
			result.append("  Prefix: " + prefix + "\r\n");
		}
		if (uri != null) {
			result.append("  Namespace URI: " + uri + "\r\n");
		}
		if (value != null) {
			result.append("  Value: " + value + "\r\n");
		}
		if (textCont != null) {
			result.append("  Text Content: " + textCont + "\r\n");
		}
		
		out.write(result.toString());
		out.write("\r\n");
		out.flush();

		nodeCount++;

	}
	
}