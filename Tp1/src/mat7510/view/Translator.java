package mat7510.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mat7510.smartBuilding.model.DeviceAction;
import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.DeviceEvent;
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

        }
    }

    public void reload() throws SmartBuildingException {
            this.model.loadConfig();
            devDrivers = this.model.getDeviceDrivers();
    }


    public DeviceDriver getDeviceDriver(String deviceID) throws SmartBuildingException{
            Iterator it = devDrivers.iterator();

            while(it.hasNext()){
                DeviceDriver driver = (DeviceDriver) it.next();

                if(driver.getDeviceID().equals(deviceID))
                    return driver;
            }

            return null;
    }

    public ArrayList<String> getDriverIds() throws SmartBuildingException{
            ArrayList<String> result = new ArrayList();
            Iterator it = devDrivers.iterator();

            while(it.hasNext()){
                DeviceDriver driver = (DeviceDriver) it.next();
                result.add(driver.getDeviceID());
            }
        return result;
    }

    public ArrayList<String> getDriverStates(String deviceID) throws SmartBuildingException{
        DeviceDriver driver = getDeviceDriver(deviceID);
        Map<String,String> map = driver.getState();

        Iterator it = map.keySet().iterator();
        ArrayList<String> result = new ArrayList();

        while(it.hasNext()){
            String key = (String) it.next();
            String value = map.get(key);
            result.add(key+" "+value);
        }

        return result;
    }

    public ArrayList<String> getDriverActionsIds(String deviceID) throws SmartBuildingException{
        DeviceDriver driver = getDeviceDriver(deviceID);
        Iterator it = driver.getActions().iterator();
        ArrayList<String> result = new ArrayList();

        while(it.hasNext()){
            DeviceAction action = (DeviceAction) it.next();
            result.add(action.getActionName());
        }

        return result;
    }

    public ArrayList<String> getDriverEventsIds(String deviceID) throws SmartBuildingException{
        DeviceDriver driver = getDeviceDriver(deviceID);
        Iterator it = driver.getEvents().iterator();
        ArrayList<String> result = new ArrayList();

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



}
