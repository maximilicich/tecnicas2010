package mat7510.smartBuilding.test;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuilding.model.XMLTranslator;

public class XMLTranslatorTest {

	static String xml = 
	"<deviceDriver> " +
	"<deviceID>DETECTOR-MOVIMIENTO-PB-0023</deviceID>" +
	"<deviceDescription>DETECTOR MOVIMIENTO PB nro 0023</deviceDescription>" +
	"<driverClass>mat7510.smartBuildingDriverDoor.DeviceDriverDoor</driverClass>" +
	"</deviceDriver>";

	
	public static void main(String[] args) throws UnsupportedEncodingException, SmartBuildingException {
		
		XMLTranslator xt = new XMLTranslator();
		
		DeviceDriver dd = xt.getDeviceDriver(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		
		System.out.println(dd.getDeviceID());
		System.out.println(dd.getDeviceDescription());
		System.out.println(dd.getClass().getName());
	}
	
}
