package mat7510.playground.xml.dom.apps;

import java.io.InputStream;

import mat7510.playground.xml.dom.DOMUtils;
import mat7510.playground.xml.dom.PropertyPrinter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * App
 * Toma los elements "apellido" de clientes.xml
 * y los imprime a c/u (solo los elementos)
 * 
 * @author MA_Xx
 *
 */
public class GetElementByTagNameExample {

	private static PropertyPrinter printer = new PropertyPrinter();
	
	public static void main(String[] args) {
		
		try {
			
			// Document document = DOMUtils.getDocument("C:/temp/clientes.xml");
			InputStream xmlFile = XMLProvider.INSTANCE.getXMLResource("clientes.xml");
			Document document = DOMUtils.getDocument(xmlFile);
			NodeList apellidos = document.getElementsByTagName("apellido");
			for (int i = 0; i < apellidos.getLength(); i++) {
				Node apellido = apellidos.item(i);
				printer.writeNode(apellido);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
