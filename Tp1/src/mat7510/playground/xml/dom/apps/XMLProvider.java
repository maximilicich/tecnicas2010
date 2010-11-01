package mat7510.playground.xml.dom.apps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Singleton para ubicar a los archivos XML
 * como resources
 * o como Files en el File System
 * 
 * @author MA_Xx
 *
 */
public enum XMLProvider {

	INSTANCE;

	public InputStream getXMLResource(String xmlName) {
		return this.getClass().getResourceAsStream("res/" + xmlName);
	}
	
	public InputStream getXMLFile(String xmlFilePath) throws FileNotFoundException {
		return new FileInputStream(new File(xmlFilePath));
	}
	
}
