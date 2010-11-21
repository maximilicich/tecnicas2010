/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import mat7510.smartBuilding.model.DeviceDriver;
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
    Translator translator;
    ArrayList<DeviceDriver> devDrivers;

    public Mediator(SmartBuildingManager buildingManager){
        mainFrame = new MainFrame();
        translator = new Translator(buildingManager);
        createWindow();
        init();
    }

    private void init(){
    	
        try {
           Set<DeviceDriver> setDrivers = translator.actualizar();
           devDrivers = new ArrayList();
           Iterator<DeviceDriver> it = setDrivers.iterator();

            while(it.hasNext()){
                DeviceDriver devDriver = (DeviceDriver) it.next();
                devDrivers.add(devDriver);
                String deviceID=devDriver.getDeviceID();
                driversListPanel.add(deviceID);
            }

        } catch (SmartBuildingException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        
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

    private void loadState(DeviceDriver driver){
          Map<String, String> map = driver.getState();
          Iterator it = map.keySet().iterator();

          while(it.hasNext()){
            String key = (String) it.next();
            String value = map.get(key);
            String data=key+" = "+value;
            stateListPanel.add(data);
          }
    }

    private void clearDriver(){
        actionListPanel.clear();
        stateListPanel.clear();
        eventListPanel.clear();
    }

    public void selectDriverWithIndex(int index){
        System.out.println("index: "+index);
          clearDriver();
          DeviceDriver driver = devDrivers.get(index);
          loadState(driver);
          actionListPanel.addAll(driver.getActions().iterator());
          eventListPanel.addAll(driver.getEvents().iterator());
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

    public void addEvent(){
        new AddEventDialog(mainFrame,this);
    }

    public void addEvent2(String txt){
        JOptionPane.showMessageDialog(mainFrame, "url: "+txt);
    }

}
