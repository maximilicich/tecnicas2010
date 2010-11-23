package mat7510.smartBuilding.remote;

import java.rmi.*;
import java.util.Set;

import mat7510.smartBuilding.model.DeviceDriver;
import mat7510.smartBuilding.model.SmartBuildingManager;

/***********************************************************************************
 * Servidor RMI. Exporta objetos del tipo RemoteObject y muestra los vínculos (bindings)
 * Ejecución: antes hay que asegurarse que hay _stub y arrancarar rmiregistry en cmd
 ***********************************************************************************/
public class Server {
	public static void main(String[] args) {
		try {
			System.out.println("Servidor iniciado");

			SmartBuildingManager.getInstance().loadConfig();
			
			Set<DeviceDriver> devices = SmartBuildingManager.getInstance().getDeviceDrivers();
			
			for ( DeviceDriver device : devices){
				RemoteObject h1 = new RemoteObject(device);
				Naming.rebind("nombre_saludo1", h1);		
			}

			System.out.println("Servidor activo y objeto remoto a la espera de llamadas");
			mostrarBindings("");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

   /********************************************************************************
    * Muestra los vínculos (bindings) de un registro cuyo nombre es 'nombre_registro' 
    *******************************************************************************/
   public static void mostrarBindings( String nombre_registro ) {
      try {
    	  String[] bindings = Naming.list( nombre_registro );
    	  System.out.println( "Vínculos disponibles:");
    	  for ( int i = 0; i < bindings.length; i++ )
    		  System.out.println( bindings[i] );
      }
      catch (Exception e) {
    	  e.printStackTrace();
      }

   }
}
