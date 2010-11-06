package mat7510.xml;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * 
 * @author Grupo 10
 *
 */
class ElementByNameFilter implements NodeFilter {

	/**
	 * El nombre por el cual filtraremos los Elements
	 */
	private String elementName;
	
	/**
	 * 
	 * @param elementName
	 */
	public ElementByNameFilter(String elementName) {
		this.elementName = elementName;
	}
	
	@Override
	/**
	 * Acepta el Nodo solo si:
	 * a) es un Element
	 * b) su nombre es el nombre filtrado
	 */
	public short acceptNode(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			if (node.getNodeName().equals(elementName))
				return NodeFilter.FILTER_ACCEPT;
		}
		return NodeFilter.FILTER_REJECT;

	}
	
}
