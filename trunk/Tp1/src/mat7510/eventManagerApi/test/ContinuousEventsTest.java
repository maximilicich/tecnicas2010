package mat7510.eventManagerApi.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import mat7510.eventManagerApi.Event;
import mat7510.eventManagerApi.EventManager;
import mat7510.eventManagerApi.EventManagerFactory;
import mat7510.eventManagerApi.exceptionRegisterEvent;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionCommand;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionReceiver;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContinuousEventsTest {

	private EventManager mngr;
	private BasicActionReceiver receiver = new BasicActionReceiver();
	private BasicActionCommand cmd = new BasicActionCommand(receiver);
	
	public ContinuousEventsTest() throws exceptionRegisterEvent {
		mngr = EventManagerFactory.getInstance().createEventManager();
		mngr.reset();

		// Registramos la secuencia A B C D 
		List<Event> list = new ArrayList<Event>();
		list.add(new BasicEvent("A"));
		list.add(new BasicEvent("B"));
		list.add(new BasicEvent("C"));
		list.add(new BasicEvent("D"));
		
		mngr.registerEventsWithOrderContinuousWithNoCancellations(cmd, list);
		
	}
	
	@Before
	public void setUp() throws Exception {
		receiver.setState(false);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContinuousEventsWithOrderABCD() throws exceptionRegisterEvent {

		List<String> cadenaDeEventos = new ArrayList<String>(4);
		cadenaDeEventos.add("A");
		cadenaDeEventos.add("B");
		cadenaDeEventos.add("C");
		cadenaDeEventos.add("D");

		Boolean initialState = false;
		Boolean finalState = true;
				
		makeItHappenAndTest(cadenaDeEventos, initialState, finalState);
		
	}

	@Test
	public void testContinuousEventsWithOrderABCDABCD() throws exceptionRegisterEvent {

		List<String> cadenaDeEventos = new ArrayList<String>(4);
		cadenaDeEventos.add("A");
		cadenaDeEventos.add("B");
		cadenaDeEventos.add("C");
		cadenaDeEventos.add("D");

		Boolean initialState = false;
		Boolean finalState = true;
				
		makeItHappenAndTest(cadenaDeEventos, initialState, finalState);

		makeItHappenAndTest(cadenaDeEventos, initialState, finalState);
		
	}

	@Test
	public void testContinuousEventsWithOrderABCXD() throws exceptionRegisterEvent {

		List<String> cadenaDeEventos = new ArrayList<String>(4);
		cadenaDeEventos.add("A");
		cadenaDeEventos.add("B");
		cadenaDeEventos.add("C");
		cadenaDeEventos.add("X");
		cadenaDeEventos.add("D");

		Boolean initialState = false;
		Boolean finalState = false;
				
		makeItHappenAndTest(cadenaDeEventos, initialState, finalState);
		
	}

	@Test
	public void testContinuousEventsWithOrderABCXABCD() throws exceptionRegisterEvent {

		List<String> cadenaDeEventos = new ArrayList<String>(4);
		cadenaDeEventos.add("A");
		cadenaDeEventos.add("B");
		cadenaDeEventos.add("C");
		cadenaDeEventos.add("X");
		cadenaDeEventos.add("A");
		cadenaDeEventos.add("B");
		cadenaDeEventos.add("C");
		cadenaDeEventos.add("D");

		Boolean initialState = false;
		Boolean finalState = true;
				
		makeItHappenAndTest(cadenaDeEventos, initialState, finalState);
		
	}


	@Test
	public void testContinuousEventsWithOrderACBD() throws exceptionRegisterEvent {
		
		List<String> cadenaDeEventos = new ArrayList<String>(4);
		cadenaDeEventos.add("A");
		cadenaDeEventos.add("C");
		cadenaDeEventos.add("B");
		cadenaDeEventos.add("D");

		Boolean initialState = false;
		Boolean finalState = false;
				
		makeItHappenAndTest(cadenaDeEventos, initialState, finalState);

		
	}

	
	
	@Test
	public void testContinuousEventsWithOrderAABCCDD() throws exceptionRegisterEvent {

		// ejecutamos la secuencia
		// Pero esta vez duplicando los eventos

		List<String> cadenaDeEventos = new ArrayList<String>(4);
		cadenaDeEventos.add("A");
		cadenaDeEventos.add("A");
		cadenaDeEventos.add("B");
		cadenaDeEventos.add("B");
		cadenaDeEventos.add("C");
		cadenaDeEventos.add("C");
		cadenaDeEventos.add("D");
		cadenaDeEventos.add("D");

		Boolean initialState = false;
		Boolean finalState = false;
				
		makeItHappenAndTest(cadenaDeEventos, initialState, finalState);
		
	}

	/**
	 * Setea el estado inicial del receiver segun el valor recibido
	 * como estado inicial
	 * 
	 * Luego "Hace suceder" la serie de eventos recibidos en la lista
	 * informando al manager via eventOcurred()
	 * Y luego de cada evento chequea que el estado no haya cambiado
	 * 
	 * Al finalizar el ultimo evento verifica que el estado sea 
	 * al valor recibido como estado final
	 *  
	 * @param events
	 * @param initialState
	 * @param finalState
	 */
	
	private void makeItHappenAndTest(List<String> events, Boolean initialState, Boolean finalState) {
		
		// Armamos un string con la secuencia de mensajes
		// en caso de tener que informar un error
		String secuencia = "";
		for (String string : events) {
			secuencia = secuencia.concat(string == null ? "null" : string);
			secuencia = secuencia.concat("-");
		}
		
		receiver.setState(initialState);
		
		for (String string : events) {
			assertEquals("Secuencia: " + secuencia + " > El receiver no tiene el estado que esperábamos (estado inicial)...", 
					initialState, receiver.getState());
			mngr.eventOccurred(new BasicEvent(string));
			
		}
		// El estado final debe ser como lo esperamos:
		assertEquals("Secuencia: " + secuencia + " > El receiver no tiene el estado que esperábamos (estado inicial)...", 
				finalState, receiver.getState());
	}
	
}

