
package mat7510.eventManagerApi.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionCommand;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionReceiver;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEvent;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEventSource;
import mat7510.eventManagerApi.version1.Event;
import mat7510.eventManagerApi.version1.EventManager;
import mat7510.eventManagerApi.version1.EventManagerFactory;
import mat7510.eventManagerApi.version1.RegisterEventException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MultipleEventsWithOrderAndCancelablesTest {

	private static final String EVENTO1 = "evento 1";
	private static final String EVENTO2 = "evento 2";
	private static final String EVENTO3 = "evento 3";
	private static final String EVENTO4 = "evento 4";

	private EventManager mngr;
	private BasicEventSource eventSource1;
	private BasicEventSource eventSource2;
	private BasicEventSource eventSource3;
	private BasicEventSource eventSource4;
	private BasicActionReceiver actionReceiver;

	@Before
	public void setUp() throws Exception {
		mngr = EventManagerFactory.getInstance().createEventManager();

		eventSource1 = new BasicEventSource(EVENTO1);
		eventSource2 = new BasicEventSource(EVENTO2);
		eventSource3 = new BasicEventSource(EVENTO3);
		eventSource4 = new BasicEventSource(EVENTO4);

		eventSource1.addListener(mngr);
		eventSource2.addListener(mngr);
		eventSource3.addListener(mngr);
		eventSource4.addListener(mngr);

		actionReceiver = new BasicActionReceiver();
	}

	@After
	public void tearDown() {
		// nada por ahora...
	}

	@Test
	public void testBasicContext() throws RegisterEventException {

		// Registramos en el Manager la accion - evento
		List<Event>events = new ArrayList<Event>();
		events.add(new BasicEvent(EVENTO1));
		events.add(new BasicEvent(EVENTO2));
		events.add(new BasicEvent(EVENTO3));
		try{
			mngr.registerEventsWithOrderDiscontinuousWithCancellations(new BasicActionCommand(actionReceiver), events);
			mngr.registerCancellables(new BasicEvent(EVENTO4),new BasicEvent(EVENTO1));
		}catch(RegisterEventException e){
			System.out.println(e.toString());
		}

		// El Source dispara el Evento...
		eventSource1.triggerEvent();
		eventSource2.triggerEvent();

		//Deberia borrar el estado de event1 y event2
		eventSource4.triggerEvent();

		eventSource1.triggerEvent();
		eventSource3.triggerEvent(); 

		// Y si todo funciona bien, el Receiver no deberia haber sufrido
		// el cambio de estado, deberia estar solo activado event1
		assertFalse(actionReceiver.getState());

		eventSource2.triggerEvent();
		eventSource3.triggerEvent();

		// Y si todo funciona bien, el Receiver  deberia haber sufrido
		// el cambio de estado
		assertTrue(actionReceiver.getState());
	}
}
