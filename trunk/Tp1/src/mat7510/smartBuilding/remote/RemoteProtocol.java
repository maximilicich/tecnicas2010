package mat7510.smartBuilding.remote;

import java.rmi.*;

import mat7510.smartBuilding.model.DeviceDriver;

/***********************************************************************************
 * Interfaz de clase exportada 
 ***********************************************************************************/

public interface RemoteProtocol extends Remote {
	public DeviceDriver get() throws RemoteException;
}
