package mat7510.playground.xml.dom.apps;

import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import mat7510.playground.xml.dom.DOMUtils;

import org.w3c.dom.Document;

/**
 * Ejemplo adaptado de 
 * http://download.oracle.com/javaee/1.4/tutorial/doc/JAXPXSLT4.html
 * 
 * Agarramos un XML y lo imprimimos a system.out
 * con el transformer de DOM a XML
 * 
 * @author MA_Xx
 *
 */
public class XMLOut {

	public static void main(String[] args) throws Exception {

		InputStream xmlFile = XMLProvider.INSTANCE.getXMLResource("clientes.xml");
		Document document = DOMUtils.getDocument(xmlFile);

		// Use a Transformer for output
		TransformerFactory tFactory =
			TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

	}

}
