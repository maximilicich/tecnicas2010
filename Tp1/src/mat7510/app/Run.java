package mat7510.app;

import mat7510.smartBuilding.model.SmartBuildingManager;
import mat7510.view.MainWindow;

public class Run {
	
   static public void main(String argv[]){
	   
	   	SmartBuildingManager buildingManager = new SmartBuildingManager();
	   	
        MainWindow mainWindow = new MainWindow(buildingManager);     
        mainWindow.getMediador().showWindow();

    }

}


