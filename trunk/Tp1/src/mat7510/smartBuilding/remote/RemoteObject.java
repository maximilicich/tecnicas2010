package mat7510.smartBuilding.remote;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

import mat7510.smartBuilding.model.DeviceDriver;

/***********************************************************************************
 * Implementación de clase exportada 
 ***********************************************************************************/
public class RemoteObject extends UnicastRemoteObject implements RemoteProtocol {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DeviceDriver device;

	public RemoteObject( DeviceDriver device ) throws RemoteException {
	    this.device = device;
	}

	public DeviceDriver get() { return this.device; }
}