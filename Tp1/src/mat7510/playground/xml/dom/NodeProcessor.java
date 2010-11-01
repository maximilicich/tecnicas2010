package mat7510.playground.xml.dom;

import org.w3c.dom.Node;

/**
 * Procesa un Nodo DOM
 * Sirve como STRATEGY para el DocumentTraversal
 * @author MA_Xx
 *
 */
public interface NodeProcessor {

	/**
	 * Procesa un Nodo DOM 
	 * @param node
	 * @throws Exception 
	 */
	public void process(Node node) throws Exception;
}
