package mat7510.eventManagerApi.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mat7510.eventManagerApi.ActionHandler;
import mat7510.eventManagerApi.Event;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionCommand;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicActionReceiver;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEvent;



public class ActionHandlerTest{
	
	
	private ActionHandler actionhandler;
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
		actionhandler.cancelEvent(index);
		assertFalse(actionhandler.isActivedEvent(index));
		index = 0;
		assertFalse(actionhandler.isActivedEvent(index));
		index = 2;
		assertFalse(actionhandler.isActivedEvent(index));

	}
	

}
