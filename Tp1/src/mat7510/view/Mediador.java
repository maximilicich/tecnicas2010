/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import javax.swing.JOptionPane;

/**
 *
 * @author sergio
 */
public class Mediador {
    MainFrame mainFrame;
    ListPanel driversListPanel;
    ListPanel stateListPanel;
    ListPanel actionListPanel;
    ListPanel eventListPanel;

    public Mediador(){
        mainFrame = new MainFrame();
        createWindow();
    }

    public void addDriverList(ListPanel list){
        driversListPanel=list;
    }

    public void addStateList(ListPanel list){
        stateListPanel=list;
    }

    public void addActionList(ListPanel list){
        actionListPanel=list;
    }

    public void addEventList(ListPanel list){
        eventListPanel=list;
    }


    private void createWindow(){
        ContentPanel contentPane = new ContentPanel(this);
        mainFrame.getContentPane().add(contentPane);
    }
    
    public void showWindow(){
        mainFrame.setVisible(true);
    }


    public void addDriverWithName(String dir){
        JOptionPane.showMessageDialog(mainFrame, "url: "+dir);
    }

    public void executeActionWithIndex(int index){
         JOptionPane.showMessageDialog(mainFrame, "ejecutar: "+index);
    }
      
    public void removeEventWithIndex(int index){
        JOptionPane.showMessageDialog(mainFrame, "remove: "+index);
    }
/*
    public void addDriverWithName(String dir){
        JOptionPane.showMessageDialog(mainFrame, "url: "+dir);
    }

    public void addDriverWithName(String dir){
        JOptionPane.showMessageDialog(mainFrame, "url: "+dir);
    }
*/
}
