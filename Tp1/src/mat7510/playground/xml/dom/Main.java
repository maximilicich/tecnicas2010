package mat7510.playground.xml.dom;

import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Main {

	public static void main(String[] args) {

		try {
			Document doc = MyDOMParserBean.getDocument("C:/temp/note.xml");

			Element rootNode = doc.getDocumentElement();

			followNode(rootNode);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static PropertyPrinter printer = new PropertyPrinter();


	// note use of recursion
	public static void followNode(Node node) throws IOException {

		printer.writeNode(node);
		if (node.hasChildNodes()) {
			Node firstChild = node.getFirstChild();
			followNode(firstChild);
		}
		Node nextNode = node.getNextSibling();
		if (nextNode != null) followNode(nextNode);

	}


}
