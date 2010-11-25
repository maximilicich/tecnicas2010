package mat7510.eventManagerApi.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionCommand;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionReceiver;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEvent;
import mat7510.eventManagerApi.version1.ActionHandler;
import mat7510.eventManagerApi.version1.Event;



public class ActionHandlerTest{
	
	
	private ActionHandler actionhandler;
	private ActionHandler actionhandlerContinuos;
	private BasicActionReceiver receiver;
	private BasicActionCommand cmd;
	private static final String EVENTO0 = "A";
	private static final String EVENTO1 = "B";
	private static final String EVENTO2 = "C";

	@Before
	public void setUp() throws Exception {
		
		receiver = new BasicActionReceiver();
		cmd = new BasicActionCommand(receiver);
		
		List<Event>events = new ArrayList<Event>();
		events.add(new BasicEvent(EVENTO0));
		events.add(new BasicEvent(EVENTO1));
		events.add(new BasicEvent(EVENTO2));
		
		actionhandler = ActionHandler.createActionGroupDiscontinuousWithCancellations(cmd,events);
		actionhandlerContinuos = ActionHandler.createActionGroupOrderContinousWithNoCancellations(cmd,events);
		
	}
	
	@Test
	public void testActiveEvent() {		
		
		int index = 1;	
		actionhandler.activateEvent(index);
		assertTrue(actionhandler.isActivedEvent(index));
		index = 0;
		assertFalse(actionhandler.isActivedEvent(index));
		index = 2;
		assertFalse(actionhandler.isActivedEvent(index));
		
	}
	
	@Test
	public void testCancelEvent() {
		
		int index = 1;	
		actionhandler.activateEvent(index);
		assertTrue(actionhandler.isActivedEvent(index));
		actionhandler.cancelEvent(index);
		assertFalse(actionhandler.isActivedEvent(index));
		index = 0;
		assertFalse(actionhandler.isActivedEvent(index));
		index = 2;
		assertFalse(actionhandler.isActivedEvent(index));

	}
	
	@Test
	public void testNotifyCancelEventDiscontinous(){
		
		int index = 1;	
		actionhandler.activateEvent(index);
		assertTrue(actionhandler.isActivedEvent(index));
		
		actionhandler.notifyCancelEvent(new BasicEvent(EVENTO1));
		assertFalse(actionhandler.isActivedEvent(index));
		
		index = 0;
		assertFalse(actionhandler.isActivedEvent(index));
		index = 2;
		assertFalse(actionhandler.isActivedEvent(index));
				
	}
	
	@Test
	public void testNotifyCancelEventContinous(){
		
		int index = 1;
		
		for ( index = 0 ; index < 3; index ++){
			actionhandlerContinuos.activateEvent(index);
			assertTrue(actionhandlerContinuos.isActivedEvent(index));
		}
		
		actionhandlerContinuos.notifyCancelEvent(new BasicEvent(EVENTO1));

		for ( index = 0 ; index < 3; index ++){
			assertFalse(actionhandlerContinuos.isActivedEvent(index));
	    }
						
	}
	
	@Test
	public void testNotifyEventContinuosOK(){
		
		int index = 0;
		
		actionhandlerContinuos.notifyEvent(new BasicEvent(EVENTO0));
	    assertTrue(actionhandlerContinuos.isActivedEvent(index));
		
	    index = 1;
	    actionhandlerContinuos.notifyEvent(new BasicEvent(EVENTO1));
	    assertTrue(actionhandlerContinuos.isActivedEvent(index));
	    
	    index = 2;
	    actionhandlerContinuos.notifyEvent(new BasicEvent(EVENTO2));
		
	    for ( index = 0 ; index < 3; index ++){
			assertFalse(actionhandlerContinuos.isActivedEvent(index));
	    }
	    
	}
	
	@Test
	public void testNotifyEventContinuosNoOK(){
		
		int index = 1;
	    
	    actionhandlerContinuos.notifyEvent(new BasicEvent(EVENTO1));
	    assertFalse(actionhandlerContinuos.isActivedEvent(index));
		
	    index = 0;
	    actionhandlerContinuos.notifyEvent(new BasicEvent(EVENTO0));
	    assertTrue(actionhandlerContinuos.isActivedEvent(index));
	    
	    index = 2;
	    actionhandlerContinuos.notifyEvent(new BasicEvent(EVENTO2));
	    
	    for ( index = 0 ; index < 3; index ++){
			assertFalse(actionhandlerContinuos.isActivedEvent(index));
	    }
	    
	}    
	
	@Test
	public void testNotifyEventDiscontinuos(){
		
		int index = 1;
	    
	    actionhandler.notifyEvent(new BasicEvent(EVENTO1));
	    assertTrue(actionhandler.isActivedEvent(index));
		
	    index = 0;
	    actionhandler.notifyEvent(new BasicEvent(EVENTO0));
	    assertTrue(actionhandler.isActivedEvent(index));
	    
	    index = 2;
	    actionhandler.notifyEvent(new BasicEvent(EVENTO2));
	    
	    for ( index = 0 ; index < 3; index ++){
			assertFalse(actionhandler.isActivedEvent(index));
	    }
	    
	}    
	
	

}
