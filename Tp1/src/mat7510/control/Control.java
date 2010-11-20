package mat7510.control;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import mat7510.eventManagerApi.version2.EventChain;
import mat7510.smartBuilding.model.DeviceAction;
import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.DeviceEvent;
import mat7510.smartBuilding.model.SmartBuildingManager;

public class Control extends Observable {

	private SmartBuildingManager model;
	
	public Control(SmartBuildingManager buildingManager){
		 this.model = buildingManager;
	}
	

	public void setNewRule (EventChain eventChain){
		this.model.getEventManager().registerEventChain(eventChain);
		
	}
	
	public List<DeviceDriver> actualizar(){
		this.model.loadConfig();
		return this.model.getDrivers();
	}
	
	public List<DeviceAction> getDriverActions ( String deviceID){
		
		
		Iterator<DeviceDriver> it = this.model.getDrivers().iterator();
		DeviceDriver deviceDriver;
		
		
		while (it.hasNext()){
			deviceDriver = it.next();
			if ( deviceDriver.getDeviceID() == deviceID ){
					return deviceDriver.getActions();
			}
		}
		return null;
	}
	
	public List<DeviceEvent> getDriverEvents ( String deviceID){
		
		
		Iterator<DeviceDriver> it = this.model.getDrivers().iterator();
		DeviceDriver deviceDriver;
		
		
		while (it.hasNext()){
			deviceDriver = it.next();
			if ( deviceDriver.getDeviceID() == deviceID ){
					return deviceDriver.getEvents();
			}
		}
		return null;
	}

	public void execute ( String deviceID, String actionName){

		List<DeviceAction> actions = this.getDriverActions(deviceID);
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


	public void notifyObservers(Object b) {
		setChanged();
		super.notifyObservers(b);
	}
}
