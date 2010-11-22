package mat7510.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mat7510.smartBuilding.model.DeviceAction;
import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.DeviceEvent;
import mat7510.smartBuilding.model.Rule;
import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuilding.model.SmartBuildingManager;


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
            System.out.println("dff");
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
    
    public List<String> getListOfRuleEvents ( Rule rule ){
		
    	List<String> listEvents = new ArrayList<String>();
    	Iterator<DeviceEvent> it = rule.getDeviceEvents().iterator();
    	
    	while ( !it.hasNext()){
    		listEvents.add(it.next().getEventName());
    	}
    	
    	return listEvents;
    }



}