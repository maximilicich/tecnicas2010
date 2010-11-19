/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import javax.swing.*;


/**
 *
 * @author sergio
 */
public class MainWindow extends JFrame{
    Mediator mediador;

    public MainWindow(){
        mediador = new Mediator();
    }

   static public void main(String argv[]){
        MainWindow mainWindow = new MainWindow();     
	mainWindow.mediador.showWindow();        
    }

}

