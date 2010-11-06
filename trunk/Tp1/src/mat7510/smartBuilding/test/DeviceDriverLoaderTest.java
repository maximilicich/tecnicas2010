package mat7510.smartBuilding.test;

import java.io.InputStream;
import java.util.Map;

import mat7510.smartBuilding.DeviceDriver;
import mat7510.smartBuilding.DeviceDriverLoader;
import mat7510.xml.DOMUtils;

public class DeviceDriverLoaderTest {

	public static void main(String[] args) throws Exception {
		
		(new DeviceDriverLoaderTest()).execute();
	
	}
	
	public void execute() throws Exception {
		
		InputStream xml = this.getClass().getResourceAsStream("res/deviceDriverConfig.xml");
		// InputStream xml = new FileInputStream(new File("C:/temp/deviceDriverConfig.xml"));
		
		// DOMUtils.getInstance().printDomToXml(DOMUtils.getInstance().getDocument(xml), System.out);
		
		Map<String, DeviceDriver> devDrivers = 
			DeviceDriverLoader.getInstance().getDeviceDrivers(xml);

		for (Map.Entry<String, DeviceDriver> entry : devDrivers.entrySet()) {
			System.out.println("DRIVER");
			System.out.println("Name : " + entry.getKey());
			System.out.println("State: ");
			System.out.println(formatState(entry.getValue()));
			System.out.println("");
		}
		
		
	}
	
	String formatState(DeviceDriver driver) {
		StringBuffer out = new StringBuffer("");
		Map<String, String> state = driver.getState();
		for (Map.Entry<String, String> entry : state.entrySet()) {
			out.append("key  : " + entry.getKey() + "\n");
			out.append("value: " + entry.getValue());
		}
		return out.toString();
	}

	
}
