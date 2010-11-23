package mat7510.smartBuilding.test;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashSet;
import java.util.Set;

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

		DeviceDriver dd2 = xt.getDeviceDriver(new ByteArrayInputStream(xml.getBytes("UTF-8")));

		if (dd.equals(dd2))
			System.out.println("SON IGUALES");
		else
			System.out.println("SON DISTINTOS");
		
		Set<DeviceDriver> myset = new LinkedHashSet<DeviceDriver>();
		
		myset.add(dd);
		myset.add(dd2);
		for (DeviceDriver deviceDriver : myset) {
			System.out.println(deviceDriver);
			
		}
		
		String [] strArray = new String[10];

		for(int i=0;i<strArray.length;i++)
		strArray[i] = "Element";

		Set<String> s = new LinkedHashSet<String>();

		for(int i=0; i<strArray.length;i++){
		if(!s.add(strArray[i]))
		System.out.println("Duplicate found : " + strArray[i]);
		}

		System.out.println(s.size() + " distinct words detected : " + s );

		
	}
	
}
