package mat7510.control;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import mat7510.smartBuilding.model.DeviceAction;
import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.DeviceEvent;
import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuilding.model.SmartBuildingManager;

public class Control extends Observable {

	private SmartBuildingManager model;
	
	public Control(SmartBuildingManager buildingManager){
		 this.model = buildingManager;
	}
	

//	public void setNewRule (EventChain eventChain){
//		this.model.getEventManager().registerEventChain(eventChain);
//		
//	}
	
	public Set<DeviceDriver> actualizar() throws SmartBuildingException {
		this.model.loadConfig();
		return this.model.getDeviceDrivers();
		// TODO VER COMO MANEJAR LAS EXCEPCIONES: deberian salir al usuario
		
	}
	
	public List<DeviceAction> getDriverActions ( String deviceID) throws SmartBuildingException{
		
		// TODO VER COMO MANEJAR LAS EXCEPCIONES: deberian salir al usuario
		
		Iterator<DeviceDriver> it = this.model.getDeviceDrivers().iterator();
		DeviceDriver deviceDriver;
		
		
		while (it.hasNext()){
			deviceDriver = it.next();
			if ( deviceDriver.getDeviceID() == deviceID ){
					return deviceDriver.getActions();
			}
		}
		return null;
	}
	
	public List<DeviceEvent> getDriverEvents ( String deviceID) throws SmartBuildingException{
		
		// TODO VER COMO MANEJAR LAS EXCEPCIONES: deberian salir al usuario
		
		Iterator<DeviceDriver> it = this.model.getDeviceDrivers().iterator();
		DeviceDriver deviceDriver;
		
		
		while (it.hasNext()){
			deviceDriver = it.next();
			if ( deviceDriver.getDeviceID() == deviceID ){
					return deviceDriver.getEvents();
			}
		}
		return null;
	}

	public void execute ( String deviceID, String actionName) throws SmartBuildingException{

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
