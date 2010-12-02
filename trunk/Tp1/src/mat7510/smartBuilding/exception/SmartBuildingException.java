package mat7510.smartBuilding.exception;

/**
 * Exception General del Sistema BAS SmartBuilding 
 * @author Grupo 10
 *
 */
public class SmartBuildingException extends Exception {

	public SmartBuildingException(Throwable e) {
		super(e);
	}
	
	public SmartBuildingException(String string) {
		super(string);
	}

	public SmartBuildingException(String string, Throwable e) {
		super(string, e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
