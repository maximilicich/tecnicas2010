package mat7510.xml;

import org.w3c.dom.Node;

/**
 * Procesa un Nodo DOM
 * Sirve como STRATEGY para el DocumentTraversal
 * @author MA_Xx
 *
 */
public interface NodeProcessor {

	/**
	 * DocumentTraversal recorre un DOM y "procesa" cada nodo
	 * recorrido, utilizando un NodeProcessor
	 * Cada implementacion de NodeProcessor define el comportamiento
	 * para "procesar" un Nodo DOM
	 * 
	 * @param node
	 * @throws Exception 
	 */
	public void process(Node node) throws XmlException;
}
