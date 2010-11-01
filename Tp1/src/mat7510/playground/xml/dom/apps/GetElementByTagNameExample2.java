package mat7510.playground.xml.dom.apps;

import java.io.InputStream;

import mat7510.playground.xml.dom.BlanksAndNewLinesNodeFilter;
import mat7510.playground.xml.dom.DOMTraversal;
import mat7510.playground.xml.dom.DOMUtils;
import mat7510.playground.xml.dom.PrinterNodeProcessor;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Idem GetElementByTagNameExample
 * Pero imprime no solamente al elemento sino a toda su rama
 * 
 * @author MA_Xx
 *
 */
public class GetElementByTagNameExample2 {

	public static void main(String[] args) {
		
		try {
			InputStream xmlFile = XMLProvider.INSTANCE.getXMLResource("clientes.xml");
			// InputStream xmlFile = XMLProvider.INSTANCE.getXMLFile("C:/temp/clientes.xml");
			Document document = DOMUtils.getDocument(xmlFile);
			
			NodeList apellidos = document.getElementsByTagName("apellido");
			for (int i = 0; i < apellidos.getLength(); i++) {
				Node apellido = apellidos.item(i);
				DOMTraversal.traverseNode(apellido, 
										  new PrinterNodeProcessor(), 
										  new BlanksAndNewLinesNodeFilter());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
