package mat7510.eventManagerApi.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionCommand;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionReceiver;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEvent;
import mat7510.eventManagerApi.version1.Event;
import mat7510.eventManagerApi.version1.EventManager;
import mat7510.eventManagerApi.version1.EventManagerFactory;
import mat7510.eventManagerApi.version1.RegisterEventException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContinuousEventsTest {

	private EventManager mngr;
	private BasicActionReceiver receiver = new BasicActionReceiver();
	private BasicActionCommand cmd = new BasicActionCommand(receiver);
	
	public ContinuousEventsTest() throws RegisterEventException {
		
		mngr = EventManagerFactory.getInstance().createEventManager();
		mngr.reset();
		
	}
	
	@Before
	public void setUp() throws Exception {
		register_ABCD_WithOrderContinuousWithNoCancellations();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void testABCXABCD() throws RegisterEventException {

		List<String> eventChain = new ArrayList<String>();
		Boolean initialState;
		Boolean finalState;

		eventChain.clear();
		eventChain.add("A");
		eventChain.add("B");
		eventChain.add("C");
		eventChain.add("X");
		eventChain.add("A");
		eventChain.add("B");
		eventChain.add("C");
		eventChain.add("D");
		
		initialState = false;
		finalState = true;
		
		makeItHappenAndTest(eventChain, initialState, finalState);
		
	}

	@Test
	public void testABCD() throws RegisterEventException {

		// El caso mas facil y el unico positivo: A B C D

		List<String> eventChain = new ArrayList<String>();
		Boolean initialState;
		Boolean finalState;

		eventChain.clear();
		eventChain.add("A");
		eventChain.add("B");
		eventChain.add("C");
		eventChain.add("D");
		
		initialState = false;
		finalState = true;
		
		makeItHappenAndTest(eventChain, initialState, finalState);

		// Repetimos la situacion tal cual...deberia volver a dar 
		makeItHappenAndTest(eventChain, initialState, finalState);

	}

	
	@Test
	public void testABCXD() throws RegisterEventException {

		List<String> eventChain = new ArrayList<String>();
		Boolean initialState;
		Boolean finalState;

		eventChain.clear();
		eventChain.add("A");
		eventChain.add("B");
		eventChain.add("C");
		eventChain.add("X");
		eventChain.add("D");
		
		initialState = false;
		finalState = false;
		
		makeItHappenAndTest(eventChain, initialState, finalState);
		
	}


	@Test
	public void testACBD() throws RegisterEventException {

		List<String> eventChain = new ArrayList<String>();
		Boolean initialState;
		Boolean finalState;

		eventChain.clear();
		eventChain.add("A");
		eventChain.add("C");
		eventChain.add("B");
		eventChain.add("D");
		
		initialState = false;
		finalState = false;
		
		makeItHappenAndTest(eventChain, initialState, finalState);
		
	}

	
	@Test
	public void testAABBCCDD() throws RegisterEventException {

		List<String> eventChain = new ArrayList<String>();
		Boolean initialState;
		Boolean finalState;

		eventChain.clear();
		eventChain.add("A");
		eventChain.add("A");
		eventChain.add("B");
		eventChain.add("B");
		eventChain.add("C");
		eventChain.add("C");
		eventChain.add("D");
		eventChain.add("D");
		
		initialState = false;
		finalState = false;
		
		makeItHappenAndTest(eventChain, initialState, finalState);
		
	}

	@Test
	public void testAllAtOnce() throws RegisterEventException {

		testABCD();
		testABCXD();
		testABCXABCD();
		testACBD();
		testAABBCCDD();
		
	}



	/**
	 * @throws exceptionRegisterEvent 
	 * 
	 */
	private void register_ABCD_WithOrderContinuousWithNoCancellations() throws RegisterEventException {
		
		mngr.reset();
		
		// Registramos la secuencia A B C D
		// Sera la secuencia asociada al comando
		List<Event> eventList = new ArrayList<Event>();
		eventList.add(new BasicEvent("A"));
		eventList.add(new BasicEvent("B"));
		eventList.add(new BasicEvent("C"));
		eventList.add(new BasicEvent("D"));

		mngr.registerEventsWithOrderContinuousWithNoCancellations(cmd, eventList);
		
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

