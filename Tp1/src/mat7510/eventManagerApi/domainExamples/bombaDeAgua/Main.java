package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mat7510.eventManagerApi.Event;
import mat7510.eventManagerApi.EventManager;
import mat7510.eventManagerApi.EventManagerFactory;
import mat7510.eventManagerApi.exceptionRegisterEvent;

public class Main {

	public static void main(String[] args) {
		
		EventManager mngr = EventManagerFactory.createEventManager();
		
		// LOS EVENT SOURCES:
		TanqueDeAgua tanque = new TanqueDeAgua(new BigDecimal(10000));
		MedidorDePresionDeAgua medidor = new MedidorDePresionDeAgua(RedDeAbastecimientoDeAgua.instance());

		tanque.addEventListener(mngr);
		medidor.addEventListener(mngr);

		// EL RECEIVER DE LAS ACCIONES:
		BombaDeAgua bomba = new BombaDeAgua();

		List<Event> eventList = new ArrayList<Event>();
		eventList.add(new TanqueVacioEvent(tanque));
		eventList.add(new HayPresionEvent(medidor));

                try{
                    // REGISTRAMOS LOS COMANDOS - eVENTOS:
                    mngr.register(new EncenderBombaCmd(bomba), eventList);
                    mngr.register(new ApagarBombaCmd(bomba), new TanqueLlenoEvent(tanque));
                    mngr.register(new ApagarBombaCmd(bomba), new NoHayPresionEvent(medidor));

                    // Las cancelaciones mutuas:
                    mngr.registerCancellables(new TanqueLlenoEvent(tanque), new TanqueVacioEvent(tanque));
                    mngr.registerCancellables(new HayPresionEvent(medidor), new NoHayPresionEvent(medidor));
                }catch(exceptionRegisterEvent e){
                    System.out.println(e.toString());
                }
		// LISTO PARA LA ACCION...
		
	}
	
}
