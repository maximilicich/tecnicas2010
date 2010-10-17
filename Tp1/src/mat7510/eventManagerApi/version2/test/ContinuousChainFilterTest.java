package mat7510.eventManagerApi.version2.test;

import static org.junit.Assert.*;
import mat7510.eventManagerApi.version2.ActionEventChain;
import mat7510.eventManagerApi.version2.ContinuousEventChainFilter;
import mat7510.eventManagerApi.version2.EventChain;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicActionCommand;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicActionReceiver;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicEvent;

import org.junit.Before;
import org.junit.Test;


public class ContinuousChainFilterTest {

	private BasicActionReceiver actionReceiver;
	private EventChain chain;
	private final String ASSERT_MSG_PREFIX = "[A-B-C Continuo] ";
	
	@Before
	public void setUp() {
		
		actionReceiver = new BasicActionReceiver();
		
		chain = new ContinuousEventChainFilter(new ActionEventChain(new BasicActionCommand(actionReceiver)));
		chain.addEvent(new BasicEvent("A"));
		chain.addEvent(new BasicEvent("B"));
		chain.addEvent(new BasicEvent("C"));

	}
	
	@Test
	public void testOrderedContinuousChain() {
		
		chain.eventOccurred(new BasicEvent("A"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A-B-C y la accion no ocurrio!",actionReceiver.getState());
		
	}

	@Test
	public void testUnorderedContinuousChain() {
		
		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("A"));
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio B-C-A y la accion no ocurrio!",actionReceiver.getState());
		
	}

	
	@Test
	public void testOrderedDiscontinuousChain() {
		
		chain.eventOccurred(new BasicEvent("A"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("K"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B-K y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B-K-C y la accion ocurrio!",actionReceiver.getState());
		
	}

	
	@Test
	public void testUnorderedDiscontinuousChain() {
		
		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("K"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("A"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K-A y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K-A-C y la accion ocurrio!",actionReceiver.getState());
		
	}

	
	@Test
	public void testUnorderedContinuousChainResetAfterActionTriggered() {
		
		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("A"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-A y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio B-A-C y la accion no ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("B"));
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio B luego de BAC y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("A"));
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio B-A luego de BAC y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-A-C luego de BAC y la accion no ocurrio!",actionReceiver.getState());
	}

}
