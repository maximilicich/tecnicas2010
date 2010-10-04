
package mat7510.eventManagerApi.test;

import java.util.*;
import mat7510.eventManagerApi.ActionCommand;
import mat7510.eventManagerApi.Event;
import mat7510.eventManagerApi.EventListener;
import mat7510.eventManagerApi.EventManager;


/**
 *
 * @author sergio
 */import mat7510.eventManagerApi.EventManagerFactory;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

class BasicEvent1 implements Event {

	public boolean equals(Event anotherEvent) {
		// Asumimos que todo evento de esta clase es el mismo
		if (!(anotherEvent instanceof BasicEvent1)) {
			return false;
		}
		return true;
	}
}

class BasicEvent2 implements Event {

	public boolean equals(Event anotherEvent) {
		// Asumimos que todo evento de esta clase es el mismo
		if (!(anotherEvent instanceof BasicEvent2)) {
			return false;
		}
		return true;
	}
}

class BasicEvent3 implements Event {

	public boolean equals(Event anotherEvent) {
		// Asumimos que todo evento de esta clase es el mismo
		if (!(anotherEvent instanceof BasicEvent3)) {
			return false;
		}
		return true;
	}
}

class BasicEvent4 implements Event {

	public boolean equals(Event anotherEvent) {
		// Asumimos que todo evento de esta clase es el mismo
		if (!(anotherEvent instanceof BasicEvent4)) {
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
	public void triggerEvent1() {
		listener.eventOccurred(new BasicEvent1());
	}
        public void triggerEvent2() {
		listener.eventOccurred(new BasicEvent2());
	}
        public void triggerEvent3() {
		listener.eventOccurred(new BasicEvent3());
	}
        public void triggerEvent4() {
		listener.eventOccurred(new BasicEvent4());
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

public class MultipleEventsWithCancelablesTest {

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
                List<Event>events = new ArrayList<Event>();
                events.add(new BasicEvent1());
                events.add(new BasicEvent2());
                events.add(new BasicEvent3());

		mngr.register(new BasicAction(actionReceiver), events);
                mngr.registerCancellables(new BasicEvent1(),new BasicEvent4());


		// El Source dispara el Evento...
		eventSource.triggerEvent1();
                eventSource.triggerEvent3();
                eventSource.triggerEvent4();
                eventSource.triggerEvent2();        

		// Y si todo funciona bien, el Receiver no deberia haber sufrido
		// el cambio de estado, por la ejecucion del evento cancelable que deberia desactivar event1
		assertFalse(actionReceiver.getState());
	}
}