package mat7510.smartBuilding.test;

import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.DeviceDriverDAO;
import mat7510.smartBuilding.model.SmartBuildingException;

public class DeviceDriverDAOTest_addDevice {

	public static void main(String[] args) throws SmartBuildingException {
		
		DeviceDriver dev = new DeviceDriverMock01("MOCK01-20101121", "Device creado desde DeviceDriverDAOTest_addDevice");
		
		DeviceDriverDAO.getInstance().addDeviceDriver(dev);

		
		
	}
	
}
