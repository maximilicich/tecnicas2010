package mat7510.playground.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

/**
 * Para reccorrer un DOM XML
 * @author MA_Xx
 *
 */
public class DOMTraversal {
	
	/**
	 * Utilidades para
	 * recorrer un Nodo DOM, processando sucesivamente cada hijo y hermano
	 * 
	 * @param node
	 * @param processor
	 * @param filter
	 * @throws Exception
	 */
	public static void traverseNode(Node node, 
							 NodeProcessor processor, 
							 NodeFilter filter) throws Exception {
		
		NodeIterator iter =
			((DocumentTraversal)node.getOwnerDocument()).createNodeIterator(
					node, 
					NodeFilter.SHOW_ALL, 
					filter, 
					false);
		Node nextNode = iter.nextNode();
		while (nextNode != null) {
			processor.process(nextNode);
			nextNode = iter.nextNode();
		}
	}
	
	public static void traverseDocument(Document document, 
										NodeProcessor processor,
										NodeFilter filter) throws Exception {
		
		Node rootNode = document.getDocumentElement();

		traverseNode(rootNode, processor, filter);
		
	}


}
