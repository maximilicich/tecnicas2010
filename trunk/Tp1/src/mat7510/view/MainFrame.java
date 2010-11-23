/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author sergio
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame{

    public MainFrame(){
        super("Smart Building Config Manager");        
        setCloseClick();
        setLookAndFeel();
        setSize(new Dimension(640,480));
        setExtendedState( MAXIMIZED_BOTH);
      }

    private void setCloseClick(){

      addWindowListener(new WindowAdapter(){
	    public void windowClosing(WindowEvent e) {System.exit(0);}
        });
   }

   private void setLookAndFeel(){
        String laf = UIManager.getSystemLookAndFeelClassName();
	try {
            UIManager.setLookAndFeel(laf);
   	}catch (UnsupportedLookAndFeelException exc){
            System.err.println("Warning: UnsupportedLookAndFeel: " + laf);}
       catch (Exception exc) {
           System.err.println("Error loading " + laf + ": " + exc);
       }
   }
}
