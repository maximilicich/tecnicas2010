package mat7510.eventManagerApi.test;

import static org.junit.Assert.*;

import java.util.List;

import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEventSource;
import mat7510.eventManagerApi.version1.Event;
import mat7510.eventManagerApi.version1.EventManager;
import mat7510.eventManagerApi.version1.EventManagerFactory;
import mat7510.eventManagerApi.version1.RegisterEventException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class NullTriggerEvent {

	private static final String EVENTO1 = "evento 1";

	private EventManager mngr;
	private BasicEventSource eventSource1;

	@Before
	public void setUp() throws Exception {
		mngr = EventManagerFactory.getInstance().createEventManager();

		eventSource1 = new BasicEventSource(EVENTO1);		
		eventSource1.addListener(mngr);
	}

	@After
	public void tearDown() {
		// nada por ahora...
	}

	@Test
	public void testBasicContext() throws RegisterEventException {

		// Registramos en el Manager la accion - evento
		List<Event>events = null;
		Event eventTest = null;

		try{
			mngr.registerEventsWithOrderDiscontinuousWithCancellations(null, events);
			fail("Excepted exeptionRegisterEvent");
		}catch(RegisterEventException e){
			System.out.println(e.toString());
		}

		//Se verifica la validación del registro de eventos con orden
		try{
			mngr.registerEventsWithOrderDiscontinuousWithCancellations(null, null);
			fail("Excepted exeptionRegisterEvent");
		}catch(RegisterEventException e){
			System.out.println(e.toString());
		}

		//Se verifica la validación del registro de eventos sin orden
		try{
			mngr.registerEventsDiscontinuousWithCancellations(null, events);
			fail("Excepted exeptionRegisterEvent");
		}catch(RegisterEventException e){
			System.out.println(e.toString());
		}


		//Se verifica la validación del registro de evento simple
		try{
			mngr.registerEvent(null, eventTest);
			fail("Excepted exeptionRegisterEvent");
		}catch(RegisterEventException e){
			System.out.println(e.toString());
		}


		//Se verifica la validación de disparo de evento nulo
		try{
			eventSource1.triggerEventNull();
			fail("Excepted exeptionRegisterEvent");
		}
		catch(Exception e){
			System.out.println(e.toString());
		}  

	}

}
