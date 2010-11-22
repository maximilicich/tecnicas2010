/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import javax.swing.*;

import mat7510.smartBuilding.model.SmartBuildingManager;


/**
 *
 * @author sergio
 */
public class MainWindow extends JFrame{
    Mediator mediador;

    public Mediator getMediador() {
		return mediador;
	}

	public MainWindow(SmartBuildingManager buildingManager){
        mediador = new Mediator(buildingManager);
    }

}

