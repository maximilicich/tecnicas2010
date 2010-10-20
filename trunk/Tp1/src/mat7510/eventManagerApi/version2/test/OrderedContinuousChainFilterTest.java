package mat7510.eventManagerApi.version2.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mat7510.eventManagerApi.version2.ActionEventChain;
import mat7510.eventManagerApi.version2.ContinuousEventChainFilter;
import mat7510.eventManagerApi.version2.EventChain;
import mat7510.eventManagerApi.version2.EventManager;
import mat7510.eventManagerApi.version2.OrderedEventChainFilter;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicActionCommand;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicActionReceiver;

import org.junit.Before;
import org.junit.Test;


public class OrderedContinuousChainFilterTest {

	private BasicActionReceiver actionReceiver;
	private BasicActionReceiver actionReceiverOppositeOrder;
	private EventChain chain;
	private EventChain chainOppositeOrder;
	private final String ASSERT_MSG_PREFIX = "[A-B-C Ordenado y Continuo] ";
	
	@Before
	public void setUp() {
		
		actionReceiver = new BasicActionReceiver();
		actionReceiverOppositeOrder = new BasicActionReceiver();
		
		EventManager.getInstance().reset();
		
		chain = new OrderedEventChainFilter(new ContinuousEventChainFilter(new ActionEventChain(new BasicActionCommand(actionReceiver))));
		chain.addEvent(EventCatalog.EVENT_A);
		chain.addEvent(EventCatalog.EVENT_B);
		chain.addEvent(EventCatalog.EVENT_C);
		
		chainOppositeOrder = new OrderedEventChainFilter(new ContinuousEventChainFilter(new ActionEventChain(new BasicActionCommand(actionReceiverOppositeOrder))));
		chainOppositeOrder.addEvent(EventCatalog.EVENT_A);
		chainOppositeOrder.addEvent(EventCatalog.EVENT_B);
		chainOppositeOrder.addEvent(EventCatalog.EVENT_C);

		EventManager.getInstance().registerEventChain(chain);
		EventManager.getInstance().registerEventChain(chainOppositeOrder);
	}
	
	@Test
	public void testOrderedContinuousChain() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A-B-C y la accion no ocurrio!",actionReceiver.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A-B-C y la accion no ocurrio!",actionReceiverOppositeOrder.getState());
	}

	@Test
	public void testUnorderedContinuousChain() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ya ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C y la accion ya ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-A y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-A y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
	}

	
	@Test
	public void testOrderedDiscontinuousChain() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B-K y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B-K y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B-K-C y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B-K-C y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
	}

	
	@Test
	public void testUnorderedDiscontinuousChain() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K-A y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K-A y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K-A-C y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-K-A-C y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
	}

	
	@Test
	public void testOrderedContinuousChainResetAfterActionTriggered() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio AB y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio AB y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABC y la accion no ocurrio!",actionReceiver.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABC y la accion no ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A luego de ABC y la accion ocurrio!",actionReceiver.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A luego de ABC y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio AB luego de ABC y la accion ocurrio!",actionReceiver.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio AB luego de ABC y la accion ocurrio!",actionReceiverOppositeOrder.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio ABC luego de ABC y la accion no ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio ABC luego de ABC y la accion no ocurrio!",actionReceiverOppositeOrder.getState());
	}

}
