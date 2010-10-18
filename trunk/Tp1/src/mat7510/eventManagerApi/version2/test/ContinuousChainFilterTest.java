package mat7510.eventManagerApi.version2.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mat7510.eventManagerApi.version2.ActionEventChain;
import mat7510.eventManagerApi.version2.ContinuousEventChainFilter;
import mat7510.eventManagerApi.version2.EventChain;
import mat7510.eventManagerApi.version2.EventManager;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicActionCommand;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicActionReceiver;

import org.junit.Before;
import org.junit.Test;


public class ContinuousChainFilterTest {

	private BasicActionReceiver actionReceiver;
	private EventChain chain;
	private final String ASSERT_MSG_PREFIX = "[A-B-C Continuo] ";
	
	@Before
	public void setUp() {
		
		actionReceiver = new BasicActionReceiver();
		
		EventManager.getInstance().reset();
		
		chain = new ContinuousEventChainFilter(new ActionEventChain(new BasicActionCommand(actionReceiver)));
		chain.addEvent(EventCatalog.EVENT_A);
		chain.addEvent(EventCatalog.EVENT_B);
		chain.addEvent(EventCatalog.EVENT_C);

		EventManager.getInstance().registerEventChain(chain);
	}
	
	@Test
	public void testOrderedContinuousChain() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A-B-C y la accion no ocurrio!",actionReceiver.getState());
		
	}

	@Test
	public void testUnorderedContinuousChain() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio B-C-A y la accion no ocurrio!",actionReceiver.getState());
		
	}

	
	@Test
	public void testOrderedDiscontinuousChain() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B-K y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B-K-C y la accion ocurrio!",actionReceiver.getState());
		
	}

	
	@Test
	public void testUnorderedDiscontinuousChain() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K-A y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K-A-C y la accion ocurrio!",actionReceiver.getState());
		
	}

	
	@Test
	public void testUnorderedContinuousChainResetAfterActionTriggered() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-A y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio B-A-C y la accion no ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio B luego de BAC y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio B-A luego de BAC y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-A-C luego de BAC y la accion no ocurrio!",actionReceiver.getState());
	}

}
