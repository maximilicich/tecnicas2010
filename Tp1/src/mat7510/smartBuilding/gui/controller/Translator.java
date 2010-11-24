package mat7510.smartBuilding.gui.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mat7510.smartBuilding.model.Rule;
import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuilding.model.SmartBuildingManager;
import mat7510.smartBuilding.model.XMLTranslator;
import mat7510.smartBuilding.model.devicedriver.DeviceAction;
import mat7510.smartBuilding.model.devicedriver.DeviceDriver;
import mat7510.smartBuilding.model.devicedriver.DeviceEvent;


public class Translator{
	
    private SmartBuildingManager model;
    Set<DeviceDriver> devDrivers;

    public Translator(SmartBuildingManager buildingManager){
        try {
            this.model = buildingManager;
            reload();
        } catch (SmartBuildingException ex) {
            System.out.println("error");
        }
    }

    public void reload() throws SmartBuildingException {
            this.model.refreshDeviceDrivers();
            devDrivers = this.model.getDeviceDrivers();
    }

    
    public void addDeviceDriver(String xmlFileURL) throws FileNotFoundException, SmartBuildingException {

    	XMLTranslator xmlTranslator = new XMLTranslator();
        DeviceDriver dev = xmlTranslator.getDeviceDriver(new FileInputStream(xmlFileURL));
        this.model.addDeviceDriver(dev);
        
    }
    

    public DeviceDriver getDeviceDriver(String deviceID) throws SmartBuildingException{
            Iterator<DeviceDriver> it = devDrivers.iterator();

            while(it.hasNext()){
                DeviceDriver driver = (DeviceDriver) it.next();

                if(driver.getDeviceID().equals(deviceID))
                    return driver;
            }

            return null;
    }

    public DeviceAction getDeviceAction(String deviceID, String nameAction) throws SmartBuildingException{
        DeviceDriver driver = getDeviceDriver(deviceID);
        Iterator<DeviceAction> it = driver.getActions().iterator();

        while(it.hasNext()){
            DeviceAction action = (DeviceAction) it.next();

            if(action.getActionName().equals(nameAction))
                return action;
        }

        return null;
    }

    public DeviceEvent getDeviceEvent(String deviceID, String eventID) throws SmartBuildingException{
        DeviceDriver driver = getDeviceDriver(deviceID);
        Iterator<DeviceEvent> it = driver.getEvents().iterator();

        while(it.hasNext()){
            DeviceEvent action = (DeviceEvent) it.next();

            if(action.getEventName().equals(eventID))
                return action;
        }

        return null;
    }

    public ArrayList<String> getDriverIds() throws SmartBuildingException{
            ArrayList<String> result = new ArrayList<String>();
            Iterator<DeviceDriver> it = devDrivers.iterator();

            while(it.hasNext()){
                DeviceDriver driver = (DeviceDriver) it.next();
                result.add(driver.getDeviceID());
            }
        return result;
    }

    public ArrayList<String> getDriverStates(String deviceID) throws SmartBuildingException{
        DeviceDriver driver = getDeviceDriver(deviceID);
        Map<String,String> map = driver.getState();

        Iterator<String> it = map.keySet().iterator();
        ArrayList<String> result = new ArrayList<String>();

        while(it.hasNext()){
            String key = (String) it.next();
            String value = map.get(key);
            result.add(key+" "+value);
        }

        return result;
    }

    public ArrayList<String> getDriverActionsIds(String deviceID) throws SmartBuildingException{
        DeviceDriver driver = getDeviceDriver(deviceID);
        Iterator<DeviceAction> it = driver.getActions().iterator();
        ArrayList<String> result = new ArrayList<String>();

        while(it.hasNext()){
            DeviceAction action = (DeviceAction) it.next();
            result.add(action.getActionName());
        }

        return result;
    }

    public ArrayList<String> getDriverEventsIds(String deviceID) throws SmartBuildingException{
        DeviceDriver driver = getDeviceDriver(deviceID);
        Iterator<DeviceEvent> it = driver.getEvents().iterator();
        ArrayList<String> result = new ArrayList<String>();

        while(it.hasNext()){
            DeviceEvent event = (DeviceEvent) it.next();
            result.add(event.getEventName());
        }
        return result;
    }

    public void execute ( String deviceID, String actionName) throws SmartBuildingException{
            DeviceDriver driver = getDeviceDriver(deviceID);

            List<DeviceAction> actions = driver.getActions();
            Iterator<DeviceAction> it = actions.iterator();
            DeviceAction deviceAction;

            if (actions != null){

                    while (it.hasNext()){
                            deviceAction = it.next();
                            if ( deviceAction.getActionName() == actionName ){
                                    deviceAction.execute();
                            }
                    }
            }
    }
    
    
    public List<String> getRulesForDeviceAction (String deviceID , String actionID) throws SmartBuildingException{
    	
    	Set<Rule> rules = this.model.getRules();
    	List<String> listOfRules = new ArrayList<String>();
    	
    		for ( Rule rule : rules){
    			if ( (rule.getDeviceAction().getActionName().equals(actionID)) && (rule.getDeviceAction().getDeviceDriver().getDeviceID().equals(deviceID) )){
    					listOfRules.add(rule.getRuleID());    			
    			}
    		}	
		return listOfRules;
    }
    
    public List<String> getEventForRule ( String deviceID, String actionID , String ruleID ) throws SmartBuildingException{
    	
    	Set<Rule> rules = this.model.getRules();
    	List<String> listEvents = new ArrayList<String>();
    	
    		for ( Rule rule : rules){
    			if ( (rule.getDeviceAction().getActionName().equals(actionID)) && (rule.getDeviceAction().getDeviceDriver().getDeviceID().equals(deviceID) )){
    					listEvents = getListOfRuleEvents (rule);
    				
    			}
    		}	
		return listEvents;
    	
    }

    public Rule getRule ( String ruleID ) throws SmartBuildingException{

    	Set<Rule> rules = this.model.getRules();

    		for ( Rule rule : rules){
                    if(rule.getRuleID().equals(ruleID)){
                        return rule;
                    }
    		}
		return null;
    }
    
    public ArrayList<String> getListOfRuleEvents ( Rule rule ){
		
    	ArrayList<String> listEvents = new ArrayList<String>();
    	Iterator<DeviceEvent> it = rule.getDeviceEvents().iterator();
    	
    	while ( it.hasNext()){
                DeviceEvent event = it.next();
    		listEvents.add(event.getDeviceDriver().getDeviceID() + " : " +  event.getEventName());
    	}
    	
    	return listEvents;
    }

    public ArrayList<String> getListOfRuleType ( Rule rule ){

    	ArrayList<String> types = new ArrayList<String>();

        if(rule.isContinuous())
            types.add("Continuo");
        else types.add("Discontinuo");

        if(rule.isOrdered())
            types.add("Con Orden");
        else types.add("Sin Orden");

        return types;
    }
    
    public void deleteRule(String ruleID) throws SmartBuildingException {
            model.deleteRule(ruleID);
    }

    public void addRule(Rule rule) throws SmartBuildingException{
        model.addRule(rule);
    }

    public void disableRule(String ruleID) throws SmartBuildingException {
        model.disableRule(ruleID);
    }

    public void enableRule(String ruleID) throws SmartBuildingException {
        model.enableRule(ruleID);
    }
}
