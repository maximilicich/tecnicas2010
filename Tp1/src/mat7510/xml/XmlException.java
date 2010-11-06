package mat7510.xml;

/**
 * 
 * @author Grupo 10
 *
 */
public class XmlException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param e
	 */
	public XmlException(Throwable e) {
		super(e);
	}
	
	/**
	 * 
	 * @param s
	 */
	public XmlException(String s) {
		super(s);
	}
	
}
