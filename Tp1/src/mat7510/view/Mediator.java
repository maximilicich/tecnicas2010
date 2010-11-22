/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuilding.model.SmartBuildingManager;

/**
 *
 * @author sergio
 */
public class Mediator {
    MainFrame mainFrame;
    ListPanel driversListPanel;
    ListPanel stateListPanel;
    ListPanel actionListPanel;
    ListPanel eventListPanel;
    ListPanel rulesListPanel;
    Translator translator;


    public Mediator(SmartBuildingManager buildingManager){
        mainFrame = new MainFrame();
        translator = new Translator(buildingManager);
        createWindow();
        try {
            driversListPanel.addAll(translator.getDriverIds().iterator());
        } catch (SmartBuildingException ex) {}
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

    public void addRuleList(ListPanel list){
        rulesListPanel=list;
    }

    private void createWindow(){
        ContentPanel contentPane = new ContentPanel(this);
        mainFrame.getContentPane().add(contentPane);
    }

    public void showWindow(){
        mainFrame.setVisible(true);
    }

    private void clearDriver(){
        actionListPanel.clear();
        stateListPanel.clear();
        eventListPanel.clear();
        rulesListPanel.clear();
    }

    public void selectDriverWithIndex(String deviceID){
          clearDriver();
        try {
          stateListPanel.addAll(translator.getDriverStates(deviceID).iterator());
          actionListPanel.addAll(translator.getDriverActionsIds(deviceID).iterator());
          eventListPanel.addAll(translator.getDriverEventsIds(deviceID).iterator());
        } catch (SmartBuildingException ex) {
            JOptionPane.showMessageDialog(mainFrame, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   public void selectActionWithIndex(String actionID){
        String driverID = (String) driversListPanel.getSelectedValue();


        try {
            if(actionID==null || driverID==null || actionID.equals("") || driverID.equals("")){
                   JOptionPane.showMessageDialog(mainFrame ,"Driver o accion no seleccionada","Warning", JOptionPane.WARNING_MESSAGE);
                   return;
            }
            rulesListPanel.clear();
            List<String> rules = translator.getRulesForDeviceAction(driverID, actionID);
           if(!rules.isEmpty()){               
                rulesListPanel.addAll(rules.iterator());
           }

        } catch (SmartBuildingException ex) {
            JOptionPane.showMessageDialog(mainFrame, ex, actionID, JOptionPane.ERROR_MESSAGE);
        }
   }

    public void addDriverWithName(String dir){
        JOptionPane.showMessageDialog(mainFrame, "url: "+dir);
    }

    public void executeActionWithIndex(String actionID){
        String driverID = (String) driversListPanel.getSelectedValue();
        try {
            if(actionID==null || driverID==null || actionID.equals("") || driverID.equals("")){
                   JOptionPane.showMessageDialog(mainFrame ,"Driver o accion no seleccionada","Warning", JOptionPane.WARNING_MESSAGE);
                   return;
            }
            translator.execute(driverID, actionID);
            stateListPanel.refresh();
            stateListPanel.addAll(translator.getDriverStates(driverID).iterator());
        } catch (SmartBuildingException ex) {
            JOptionPane.showMessageDialog(mainFrame, ex, actionID, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeEventWithIndex(String eventID){
             JOptionPane.showMessageDialog(mainFrame, "", eventID, JOptionPane.ERROR_MESSAGE);
    }

    public void addEvent(){
        String driverID = (String) driversListPanel.getSelectedValue();

        if(driverID==null){
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar un driver");
            return;
        }
        try {
            ArrayList<String> actionsId = translator.getDriverActionsIds(driverID);
            Map<String,String> map = new HashMap();



            new AddEventDialog(mainFrame,this);
        } catch (SmartBuildingException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Error", "", JOptionPane.ERROR_MESSAGE);
        }        
    }

    public void createNewRule(){
        String driverID = (String) driversListPanel.getSelectedValue();

        if(driverID==null){
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar un driver");
            return;
        }
        try {
            ArrayList<String> actionsId = translator.getDriverActionsIds(driverID);
            Map<String,String> map = new HashMap();



            new NewRuleDialog(mainFrame,this);
        } catch (SmartBuildingException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Error", "", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void addEvent2(String txt){
        JOptionPane.showMessageDialog(mainFrame, "url: "+txt);
    }

}