package mat7510.smartBuilding.test;

import java.util.Map;
import java.util.Set;

import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.DeviceDriverDAO;

public class DeviceDriverLoaderTest {

	public static void main(String[] args) throws Exception {
		
		(new DeviceDriverLoaderTest()).execute();
	
	}
	
	public void execute() throws Exception {
		
		// InputStream xml = this.getClass().getResourceAsStream("res/deviceDriverConfig.xml");
		// InputStream xml = new FileInputStream(new File("C:/temp/deviceDriverConfig.xml"));
		
		// DOMUtils.getInstance().printDomToXml(DOMUtils.getInstance().getDocument(xml), System.out);
		
		Set<DeviceDriver> devDrivers = 
			DeviceDriverDAO.getInstance().getDeviceDrivers();

		for (DeviceDriver devDriver : devDrivers) {
			System.out.println("DRIVER");
			System.out.println("Device ID          : " + devDriver.getDeviceID());
			System.out.println("Device Description : " + devDriver.getDeviceDescription());
			System.out.println("State              : ");
			System.out.println(formatState(devDriver));
			System.out.println("");
		}
		
		String oneDeviceID = "DeviceDriverClock-BH368";
		
		System.out.println("Now searching for " + oneDeviceID + "...");
		
		DeviceDriver mock = DeviceDriverDAO.getInstance().getDeviceDriverByID(oneDeviceID);
		if (mock == null) {
			System.out.println(oneDeviceID + " NOT FOUND");
		} else {
			System.out.println("ONE DEVICE DRIVER FOUND...");
			System.out.println("Device ID          : " + mock.getDeviceID());
			System.out.println("Device Description : " + mock.getDeviceDescription());
			System.out.println("State              : ");
			System.out.println(formatState(mock));
			System.out.println("");

		}
		
		
	}
	
	String formatState(DeviceDriver driver) {
		StringBuffer out = new StringBuffer("");
		Map<String, String> state = driver.getState();
		for (Map.Entry<String, String> entry : state.entrySet()) {
			out.append("key  : " + entry.getKey() + " | ");
			out.append("value: " + entry.getValue() + "\n");
		}
		return out.toString();
	}

	
}
