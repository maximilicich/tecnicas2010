package mat7510.eventManagerApi.test;

import static org.junit.Assert.assertTrue;
import mat7510.eventManagerApi.ActionCommand;
import mat7510.eventManagerApi.Event;
import mat7510.eventManagerApi.EventListener;
import mat7510.eventManagerApi.EventManager;
import mat7510.eventManagerApi.EventManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class BasicEvent implements Event {

	public boolean equals(Event anotherEvent) {
		// Asumimos que todo evento de esta clase es el mismo
		if (!(anotherEvent instanceof BasicEvent)) {
			return false;
		}
		return true;
	}
}

class BasicEventSource {
	private EventListener listener;
	public void addListener(EventListener listener) {
		this.listener = listener;
	}
	public void triggerEvent() {
		listener.eventOccurred(new BasicEvent());
	}
}

class BasicActionReceiver {
	
	private boolean state = false;

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean getState() {
		return state;
	}
	
}

class BasicAction implements ActionCommand {

	BasicActionReceiver receiver;
	
	public BasicAction(BasicActionReceiver receiver) {
		this.receiver = receiver;
	}
	
	public void execute() {
		receiver.setState(true);
	}
	
}

public class BasicTest {

	private EventManager mngr;
	private BasicEventSource eventSource;
	private BasicActionReceiver actionReceiver;
	
	@Before
	public void setUp() throws Exception {
		mngr = EventManagerFactory.getInstance();
		
		eventSource = new BasicEventSource();
		eventSource.addListener(mngr);
		
		actionReceiver = new BasicActionReceiver();
	}

	@After
	public void tearDown() {
		// nada por ahora...
	}
	
	@Test
	public void testBasicContext() {
		
		// Registramos en el Manager la accion - evento
		mngr.register(new BasicAction(actionReceiver), new BasicEvent());

		// El Source dispara el Evento...
		eventSource.triggerEvent();
		
		// Y si todo funciona bien, el Receiver deberia haber sufrido
		// el cambio de estado, por la accion ejecutada...
		assertTrue(actionReceiver.getState());
	}
}
