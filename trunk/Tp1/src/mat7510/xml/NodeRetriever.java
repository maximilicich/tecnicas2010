package mat7510.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;


/**
 * Clase de Package
 * 
 * Un NodeProcessor que se encarga de CAPTURAR Nodos
 * Sirve para que al recorrer un DOM (traverse)
 * se pueda ir guardando cada nodo recorrido
 * Aplicando un filtro al recorrer, el retriever 
 * se encargara de quedarse solo con los Nodos Filtrados
 * 
 * @author Grupo 10 
 *
 */
class NodeRetriever implements NodeProcessor, Iterable<Node> {

	/**
	 * Los Nodos capturados
	 */
	private List<Node> nodes = new ArrayList<Node>();
	
	@Override
	/**
	 * La tarea del Retriever es CAPTURAR al Nodo
	 */
	public void process(Node node) throws XmlException {
		nodes.add(node);
		
	}
	
	/**
	 * El Retriever es iterable
	 */
	public Iterator<Node> iterator() {
		return nodes.iterator();
	}
	
	/**
	 * Cuantos nodos se capturaron hasta ahora ?
	 * 
	 * @return
	 */
	public int howMany() {
		return nodes.size();
	}
	
}
