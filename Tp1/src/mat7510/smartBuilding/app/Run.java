package mat7510.smartBuilding.app;

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

		SmartBuildingManager.getInstance().loadConfig();
		
		//MainWindow mainWindow = new MainWindow(SmartBuildingManager.getInstance());     
		//mainWindow.getMediador().showWindow();
		
		Mediator mediator = new Mediator(SmartBuildingManager.getInstance());
		mediator.showWindow();
	}

}
