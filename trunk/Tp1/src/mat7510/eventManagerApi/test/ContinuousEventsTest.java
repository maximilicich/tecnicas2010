package mat7510.eventManagerApi.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		
		// Hacemos ocurrir los eventos en el orden esperado 
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("A"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("B"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("C"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("D"));

		// el receiver debe haber recibido la accion
		assertTrue(receiver.getState());

		
	}
	
	@Test
	public void testContinuousEventsWithOrderABCXD() throws exceptionRegisterEvent {

		// ejecutamos la secuencia
		// Pero esta vez con un evento X en el medio

		receiver.setState(false);
		
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("A"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("B"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("C"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("X"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("D"));

		// Pero esta vez el receiver no debe haber recibido la accion
		assertFalse(receiver.getState());

		
	}

	@Test
	public void testContinuousEventsWithOrderACBD() throws exceptionRegisterEvent {
		
		// Hacemos ocurrir los eventos pero en otro orden 
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("A"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("C"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("B"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("D"));

		// el receiver NO debe haber recibido la accion
		assertFalse(receiver.getState());

		
	}

	
	
	@Test
	public void testContinuousEventsWithOrderAABCCDD() throws exceptionRegisterEvent {

		// ejecutamos la secuencia
		// Pero esta vez duplicando los eventos

		receiver.setState(false);
		assertFalse(receiver.getState());

		mngr.eventOccurred(new BasicEvent("A"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("A"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("B"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("B"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("C"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("C"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("D"));
		assertFalse(receiver.getState());
		mngr.eventOccurred(new BasicEvent("D"));

		// receiver no debe haber recidibo la accion
		assertFalse(receiver.getState());

		
	}

}
