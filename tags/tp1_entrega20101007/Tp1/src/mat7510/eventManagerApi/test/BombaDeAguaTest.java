package mat7510.eventManagerApi.test;


import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mat7510.eventManagerApi.Event;
import mat7510.eventManagerApi.EventManager;
import mat7510.eventManagerApi.EventManagerFactory;
import mat7510.eventManagerApi.exceptionRegisterEvent;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.ApagarBombaCmd;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.BombaDeAgua;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.CambioDePresionDeAguaEvent;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.ControlarPresionCmd;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.EncenderBombaCmd;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.HayPresionEvent;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.MedidorDePresionDeAgua;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.NoHayPresionEvent;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.RedDeAbastecimientoDeAgua;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.TanqueDeAgua;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.TanqueLlenoEvent;
import mat7510.eventManagerApi.domainExamples.bombaDeAgua.TanqueVacioEvent;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BombaDeAguaTest {

	private static EventManager mngr;
	private TanqueDeAgua tanque;
	private MedidorDePresionDeAgua medidor;
	private BombaDeAgua bomba;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mngr = EventManagerFactory.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		// Siempre reseteamos el Manager:
		mngr.reset();

		// Tenemos una bomba de agua:
		bomba = new BombaDeAgua();
		// (inicia apagada)

		// Tenemos un tanque de agua
		// con capacidad de 10.000 litros
		tanque = new TanqueDeAgua(new BigDecimal(10000));
		// Va a estar lleno cuando llegue a su 95% de capacidad:
		tanque.setLimiteSuperior(new BigDecimal(9500));
		// Y va a estar vacio cuando tenga menos de 100 litros:
		tanque.setLimiteInferior(new BigDecimal(100));
		// (el tanque inicia vacio)

		// Tenemos un Medidor de Presion de Agua
		// conectado a la Red de Abastecimiento
		medidor = new MedidorDePresionDeAgua(RedDeAbastecimientoDeAgua.instance());

		// Y vamos conectando los objetos al Manejador de Sucesos...

		// El Tanque y el Medidor son Disparadores de Sucesos
		// El Manejador escuchara los Sucesos
		tanque.addEventListener(mngr);
		medidor.addEventListener(mngr);

		// La Red de Agua TAMBIEN es disparador de Sucesos:
		RedDeAbastecimientoDeAgua.instance().addListener(mngr);

		// Y entonces "enchufamos" las acciones con sus eventos...
		registrarAccionesYEventos();

		// LISTO PARA LA ACCION!
	}

	private void registrarAccionesYEventos() throws exceptionRegisterEvent {

		// Cuando cambia la presion de la Red (evento), 
		// el medidor debe testear la presion (action)
		mngr.register(new ControlarPresionCmd(medidor), 
				new CambioDePresionDeAguaEvent());

		// Cuando el Medidor detecta que hay presion y ademas
		// el tanque indica que esta vacio (2 eventos)
		// entonces la Bomba debe encenderse:
		List<Event> eventosParaEncenderLaBomba = new ArrayList<Event>(2);
		eventosParaEncenderLaBomba.add(new HayPresionEvent(medidor));
		eventosParaEncenderLaBomba.add(new TanqueVacioEvent(tanque));
		mngr.register(new EncenderBombaCmd(bomba), eventosParaEncenderLaBomba);

		// Ahora, si no hay presion, o bien si el tanque se llena
		// entonces la bomba debe apagarse:
		mngr.register(new ApagarBombaCmd(bomba), new NoHayPresionEvent(medidor));
		mngr.register(new ApagarBombaCmd(bomba), new TanqueLlenoEvent(tanque));

		// Pot ultimo, indicamos los Eventos Cancelables entre si
		// Si el Tanque esta lleno, entonces no esta vacio
		mngr.registerCancellables(new TanqueLlenoEvent(tanque), new TanqueVacioEvent(tanque));
		mngr.registerCancellables(new TanqueVacioEvent(tanque), new TanqueLlenoEvent(tanque));

		// Y si el Medidor detecta que Hay presion, entonces Deja de "no haber presion" 
		mngr.registerCancellables(new HayPresionEvent(medidor), new NoHayPresionEvent(medidor));
		mngr.registerCancellables(new NoHayPresionEvent(medidor), new HayPresionEvent(medidor));
	}


	@After
	public void tearDown() throws Exception {
	}

	private void printEstado(String str1, String str2){
		System.out.println("");
		if(str1.length() >0)
			System.out.println("Acción: "+str1);
		else System.out.println("Estado Inicial:");
		System.out.println("Limite inferior del tanque: "+tanque.getLimiteInferior());
		System.out.println("Limite superior del tanque: "+tanque.getLimiteSuperior());
		System.out.println("Litros en el tanque: "+tanque.getContenido());

		System.out.println("Presión Mínima: "+medidor.getPresionMinima());
		System.out.println("Presión: "+RedDeAbastecimientoDeAgua.instance().getPresionActual());
		if(str2.length() >0)
			System.out.println("Resultado: "+str2);
	}

	@Test
	public void testBombaDeAguaTanqueLlenoYPresion()  {

		// La presion minima para detectar presion sera 50 PSI
		medidor.setPresionMinima(new BigDecimal(50));

		printEstado("","");
		assertTrue(! bomba.isEncendida());
		assertTrue(tanque.isTanqueVacio());

		// llenamos el tanque un poquito...
		tanque.llenar(new BigDecimal(80));

		// El tanque no se lleno, y la bomba sigue apagada
		assertTrue(! tanque.isTanqueLleno());
		assertTrue(! bomba.isEncendida());
		assertTrue(tanque.isTanqueVacio());

		printEstado("Lleno un ponco el tanque","El tanque sigue con bajo caudal pero no hay presión para encender la bomba.");

		// Damos un poquito de presion a la Red:
		RedDeAbastecimientoDeAgua.instance().setPresionActual(new BigDecimal(10));

		// El medidor aun no detecto presion y la bomba sigue apagada:
		assertTrue(! medidor.hayPresion());
		assertTrue(! bomba.isEncendida());

		printEstado("Subo un poco la presión","El tanque esta vacio y la presión sigue siendo insuficiente para encender la bomba.");

		// Ahora le damos suficiente presion a la Red,
		// cosa que arranque la bomba:
		RedDeAbastecimientoDeAgua.instance().setPresionActual(new BigDecimal(110));

		// El medidor debe haber detectado la presion
		// y la bomba DEBE HABER ENCENDIDO:

		printEstado("Subo la presión","Enciendo la bomba");

		assertTrue(tanque.isTanqueVacio());
		assertTrue(medidor.hayPresion());
		assertTrue(bomba.isEncendida());

	}

}
