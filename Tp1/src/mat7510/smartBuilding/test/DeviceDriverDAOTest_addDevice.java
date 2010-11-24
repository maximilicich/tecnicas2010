package mat7510.smartBuilding.test;

import mat7510.smartBuilding.dao.DAOFactory;
import mat7510.smartBuilding.dao.DeviceDriverDAO;
import mat7510.smartBuilding.dao.implement.DAOFactoryXMLImplementation;
import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.SmartBuildingException;

public class DeviceDriverDAOTest_addDevice {

	public static void main(String[] args) throws SmartBuildingException {

		DAOFactory daoFactory = new DAOFactoryXMLImplementation();
			
		DeviceDriverDAO deviceDriverDAO = daoFactory.createDeviceDriverDAO();
		
		DeviceDriver dev = new DeviceDriverMock01("MOCK01-20101121", "Device creado desde DeviceDriverDAOTest_addDevice");
		
		deviceDriverDAO.addDeviceDriver(dev);

		
		
	}
	
}
