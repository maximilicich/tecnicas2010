package mat7510.playground.xml.dom;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * Filtro para evitar los textos de espacios en blanco y new lines
 * 
 * FUENTE: 
 * {@link http://www.ibm.com/developerworks/xml/library/x-tipdomnf.html Tip: Using a DOM NodeFilter)
 * 
 * @author MA_Xx
 *
 */
public class BlanksAndNewLinesNodeFilter implements NodeFilter {

	@Override
	public short acceptNode(Node node) {
		if (node.getNodeType() == Node.TEXT_NODE) {
			if (node.getTextContent().trim().equalsIgnoreCase(""))
				return NodeFilter.FILTER_SKIP;
		}
		return NodeFilter.FILTER_ACCEPT;
	}
}
