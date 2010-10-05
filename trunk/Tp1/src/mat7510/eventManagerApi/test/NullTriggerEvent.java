package mat7510.eventManagerApi.test;

import static org.junit.Assert.*;
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
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEventSource;

import org.junit.Test;


public class NullTriggerEvent {
	
	
	private static final String EVENTO1 = "evento 1";

	
	private EventManager mngr;
	private BasicEventSource eventSource1;

	private BasicActionReceiver actionReceiver;


	public void setUp() throws Exception {
		
		mngr = EventManagerFactory.getInstance();

		eventSource1 = new BasicEventSource(EVENTO1);
		
		eventSource1.addListener(mngr);

		actionReceiver = new BasicActionReceiver();
	}

	@Test
	public void testBasicContext() throws exceptionRegisterEvent {

		// Registramos en el Manager la accion - evento
                List<Event>events = new ArrayList<Event>();
                events.add(new BasicEvent(EVENTO1));

               try{
                   mngr.register(new BasicActionCommand(actionReceiver), events);
               }catch(exceptionRegisterEvent e){
                   System.out.println(e.toString());
               }
		// El Source dispara el Evento...
               try{
            	   eventSource1.triggerEventNull();
               }catch(exceptionRegisterEvent e){
                   System.out.println(e.toString());
               }          
		

		// Y si todo funciona bien, el Receiver deberia haber sufrido
		// el cambio de estado, por la accion ejecutada...
		assertFalse(actionReceiver.getState());
	}

}
