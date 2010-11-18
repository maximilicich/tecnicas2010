/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;
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

}
