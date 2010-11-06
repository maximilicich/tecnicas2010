package mat7510.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

/**
 * Para reccorrer un DOM XML
 * @author Grupo 10
 *
 */
public class DOMTraversal {
	
	/**
	 * 
	 */
	private NodeFilter filter;
	
	/**
	 * 
	 */
	private NodeProcessor processor;
	
	/**
	 * 
	 * @param processor
	 */
	public DOMTraversal(NodeProcessor processor) {
		this.processor = processor;
	}
	
	/**
	 * 
	 * @param processor
	 * @param filter
	 */
	public DOMTraversal(NodeProcessor processor, NodeFilter filter) {
		this.processor = processor;
		this.filter = filter;
	}
	
	
	/**
	 * Rrecorre un Nodo DOM, processando sucesivamente cada hijo y hermano
	 * 
	 * @param node
	 * 
	 * @throws Exception
	 */
	public void traverseNode(Node node) throws XmlException {
		
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
	
	/**
	 * Procesa cada nodo del documento, comenzando por la raiz
	 * 
	 * @param document
	 * 
	 * @throws Exception
	 */
	public void traverseDocument(Document document) throws XmlException {
		
		Node rootNode = document.getDocumentElement();

		traverseNode(rootNode);
		
	}

}
