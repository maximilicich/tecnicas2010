package mat7510.smartBuilding.test;

import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuilding.model.dao.DeviceDriverDAO;
import mat7510.smartBuilding.model.dao.implement.DAOFactory;

public class DeviceDriverDAOTest_addDevice {

	public static void main(String[] args) throws SmartBuildingException {

				
		DeviceDriverDAO deviceDriverDAO = DAOFactory.getInstance().createDeviceDriverDAO();
		
		DeviceDriver dev = new DeviceDriverMock01("MOCK01-20101121", "Device creado desde DeviceDriverDAOTest_addDevice");
		
		deviceDriverDAO.addDeviceDriver(dev);

		
		
	}
	
}
