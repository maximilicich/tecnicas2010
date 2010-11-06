package mat7510.playground.xml.dom.apps;

import mat7510.playground.xml.dom.DOMTraversal;
import mat7510.playground.xml.dom.DOMUtils;
import mat7510.playground.xml.dom.ElementByNameFilter;
import mat7510.playground.xml.dom.NodeRetriever;
import mat7510.playground.xml.dom.PropertyPrinter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class GetNodeFromDocument {

	public static void main(String[] args) {

		(new GetNodeFromDocument()).execute();
		
	}

	public void execute() {
		
		PropertyPrinter printer = new PropertyPrinter();
		
		try {
			Document document = DOMUtils.getDocument(XMLProvider.INSTANCE.getXMLResource("note.xml"));

			NodeRetriever nodeRetriever = new NodeRetriever();
			
			DOMTraversal.traverseDocument(document, 
					  nodeRetriever, 
					  new ElementByNameFilter("to"));
			
			System.out.println("nodeRetriever.howMany() : " + nodeRetriever.howMany());
			
			for (Node node : nodeRetriever) {
				NodeRetriever recipientRetriever = new NodeRetriever();
				DOMTraversal.traverseNode(node, 
										  recipientRetriever,
										  new ElementByNameFilter("recipient"));
				
				System.out.println("recipientRetriever.howMany() : " + recipientRetriever.howMany());
				
				for (Node rec : recipientRetriever) {
					System.out.println(rec.getTextContent());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
