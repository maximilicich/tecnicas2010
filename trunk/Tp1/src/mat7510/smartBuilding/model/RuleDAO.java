package mat7510.smartBuilding.model;

/**
 * 
 * @author MA_Xx
 *
 */
public class RuleDAO {

	private static RuleDAO instance = new RuleDAO();
	private RuleDAO() {
	}
	
	private static final String XML_FILENAME = "res/ruleConfig.xml";
	
	/**
	 * LOS TAGS XML
	 * Son Publicos para que el Usuario pueda consultar y 
	 * entender la estructura del XML de Configuracion
	 */
	public static final String RULES_SECTION_TAG = "smartBuildingRules";
	public static final String DEVICE_DRIVER_ELEMENT_TAG = "deviceDriver";
	public static final String DEVICE_DRIVER_ELEMENT_ID_TAG = "deviceID";
	public static final String DEVICE_DRIVER_ELEMENT_NAME_TAG = "deviceDescription";
	public static final String DEVICE_DRIVER_ELEMENT_CLASS_TAG = "driverClass";
	
	/**
	 * DeviceDriverLoader Es un Singleton
	 *  
	 * @return la instancia Singleton de la Clase
	 */
	public static RuleDAO getInstance() {
		return instance;
	}
	
}
