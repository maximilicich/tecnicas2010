package mat7510.eventManagerApi.test;

import static org.junit.Assert.assertTrue;
import mat7510.eventManagerApi.EventManager;
import mat7510.eventManagerApi.EventManagerFactory;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionCommand;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionReceiver;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEvent;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEventSource;
import mat7510.eventManagerApi.RegisterEventException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicTest {

	private EventManager mngr;
	private BasicEventSource eventSource;
	private BasicActionReceiver actionReceiver;

	private static final String EVENTO = "evento";

	@Before
	public void setUp() throws Exception {
		mngr = EventManagerFactory.getInstance().createEventManager();

		eventSource = new BasicEventSource(EVENTO);
		eventSource.addListener(mngr);

		actionReceiver = new BasicActionReceiver();
	}

	@After
	public void tearDown() {
		// nada por ahora...
	}

	@Test
	public void testBasicContext() throws RegisterEventException {

		// Registramos en el Manager la accion - evento
		try{
			mngr.registerEvent(new BasicActionCommand(actionReceiver), new BasicEvent(EVENTO));
		}catch(RegisterEventException e){
			System.out.println(e.toString());
		}
		// El Source dispara el Evento...
		eventSource.triggerEvent();

		// Y si todo funciona bien, el Receiver deberia haber sufrido
		// el cambio de estado, por la accion ejecutada...
		assertTrue(actionReceiver.getState());
	}

}
