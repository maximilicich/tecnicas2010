package mat7510.smartBuilding.app;

import mat7510.smartBuilding.dao.implement.DAOFactoryXMLImplementation;
import mat7510.smartBuilding.gui.controller.Mediator;
import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuilding.model.SmartBuildingManager;

/**
 * 
 * @author Grupo 10
 *
 */
public class Run {

	/**
	 * 
	 * @param argv
	 * @throws SmartBuildingException
	 */
	static public void main(String argv[]) throws SmartBuildingException {

		SmartBuildingManager smartBuildingManager = 
			new SmartBuildingManager(new DAOFactoryXMLImplementation());
			
		smartBuildingManager.loadConfig();
		
		//MainWindow mainWindow = new MainWindow(SmartBuildingManager.getInstance());     
		//mainWindow.getMediador().showWindow();
		
		Mediator mediator = new Mediator(smartBuildingManager);
		mediator.showWindow();
	}

}
