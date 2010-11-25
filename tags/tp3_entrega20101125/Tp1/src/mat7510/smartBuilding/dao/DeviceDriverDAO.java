package mat7510.smartBuilding.dao;

import java.util.Set;

import mat7510.smartBuilding.model.SmartBuildingException;
import mat7510.smartBuilding.model.devicedriver.DeviceDriver;

/**
 * DAO para los DeviceDrivers:
 * Es el punto de comunicacion con la persistencia de las class de Dominio
 * 
 * @author Grupo 10
 *
 */
public interface DeviceDriverDAO {

	/**
	 * Este metodo devuelve la lista de DeviceDrivers configurados para SmartBuilding
	 * instanciando por Reflection a cada Driver segun la clase configurada para c/u 
	 * 
	 * @return Un Set de DeviceDrivers
	 *  
	 * @throws SmartBuildingException Excepcion general 
	 */
	public Set<DeviceDriver> getDeviceDrivers() throws SmartBuildingException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SmartBuildingException
	 */
	public DeviceDriver getDeviceDriverByID(String id) throws SmartBuildingException;

	
	/**
	 * 
	 * Vuelca todas las Rules en el Repositorio
	 * Reemplaza todas las existentes por este conjunto
	 * 
	 * La unicidad de ID esta asegurada por set un SET
	 * (y el equals de las Rules asegura la igualdad por IDs
	 * 
	 * @param rules
	 * @throws SmartBuildingException 
	 */
	public void setDeviceDrivers(Set<DeviceDriver> deviceDrivers) throws SmartBuildingException;

	
	/**
	 * 
	 * @param deviceDriver
	 * @throws SmartBuildingException
	 */
	public void addDeviceDriver(DeviceDriver deviceDriver) throws SmartBuildingException;
	
}	
