package mat7510.eventManagerApi.test;

import static org.junit.Assert.*;

import java.util.List;

import mat7510.eventManagerApi.Event;
import mat7510.eventManagerApi.EventManager;
import mat7510.eventManagerApi.EventManagerFactory;
import mat7510.eventManagerApi.exceptionRegisterEvent;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEventSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class NullTriggerEvent {
	
	private static final String EVENTO1 = "evento 1";
	
	private EventManager mngr;
	private BasicEventSource eventSource1;

	@Before
	public void setUp() throws Exception {
		mngr = EventManagerFactory.getInstance();

		eventSource1 = new BasicEventSource(EVENTO1);		
		eventSource1.addListener(mngr);
	}

	@After
	public void tearDown() {
		// nada por ahora...
	}

	@Test
	public void testBasicContext() throws exceptionRegisterEvent {

            // Registramos en el Manager la accion - evento
            List<Event>events = null;
            Event eventTest = null;
            
            try{
                mngr.registerWithOrder(null, events);
                fail("Excepted exeptionRegisterEvent");
            }catch(exceptionRegisterEvent e){
                   System.out.println(e.toString());
            }
            
          //Se verifica la validación del registro de eventos con orden
            try{
                mngr.registerWithOrder(null, null);
                fail("Excepted exeptionRegisterEvent");
            }catch(exceptionRegisterEvent e){
                   System.out.println(e.toString());
            }
            
            //Se verifica la validación del registro de eventos sin orden
            try{
                mngr.register(null, events);
                fail("Excepted exeptionRegisterEvent");
            }catch(exceptionRegisterEvent e){
                   System.out.println(e.toString());
            }
            
            
            //Se verifica la validación del registro de evento simple
            try{
                mngr.register(null, eventTest);
                fail("Excepted exeptionRegisterEvent");
            }catch(exceptionRegisterEvent e){
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
