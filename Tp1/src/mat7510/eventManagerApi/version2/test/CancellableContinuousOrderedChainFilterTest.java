package mat7510.eventManagerApi.version2.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mat7510.eventManagerApi.version2.ActionEventChain;
import mat7510.eventManagerApi.version2.CancellableEventChainFilter;
import mat7510.eventManagerApi.version2.ContinuousEventChainFilter;
import mat7510.eventManagerApi.version2.EventChain;
import mat7510.eventManagerApi.version2.EventManager;
import mat7510.eventManagerApi.version2.OrderedEventChainFilter;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicActionCommand;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicActionReceiver;
import mat7510.eventManagerApi.version2.domainExamples.basicDomain.BasicEvent;

import org.junit.Before;
import org.junit.Test;


/**
 * Aplicamos los 3 filtros a una cadena !
 * Se prueba a su vez el intercambio del orden de los filtros
 * K cancela a A, a B y a C
 * H cancela a A
 * 
 * @author Grupo 10 
 *
 */
public class CancellableContinuousOrderedChainFilterTest {

	private BasicActionReceiver actionReceiver;
	private BasicActionReceiver actionReceiverChangeOrder1;
	private BasicActionReceiver actionReceiverChangeOrder2;
	private EventChain chain;
	private EventChain chainChangeOrder1;
	private EventChain chainChangeOrder2;
	private final String ASSERT_MSG_PREFIX = "[A-B-C Cancelable Continuo y Ordenado] ";

	
	@Before
	public void setUp() {
		
		EventManager.getInstance().reset();
		EventManager.getInstance().setCancellableFor(EventCatalog.EVENT_K, EventCatalog.EVENT_A);
		EventManager.getInstance().setCancellableFor(EventCatalog.EVENT_K, EventCatalog.EVENT_B);
		EventManager.getInstance().setCancellableFor(EventCatalog.EVENT_K, EventCatalog.EVENT_C);
		EventManager.getInstance().setCancellableFor(EventCatalog.EVENT_H, new BasicEvent("Y"));
		EventManager.getInstance().setCancellableFor(EventCatalog.EVENT_H, EventCatalog.EVENT_A);
		EventManager.getInstance().setCancellableFor(EventCatalog.EVENT_A, EventCatalog.EVENT_J);
		EventManager.getInstance().setCancellableFor(EventCatalog.EVENT_L, EventCatalog.EVENT_K);

		actionReceiver = new BasicActionReceiver();
		actionReceiverChangeOrder1 = new BasicActionReceiver();
		actionReceiverChangeOrder2 = new BasicActionReceiver();

		chain = new CancellableEventChainFilter(new ContinuousEventChainFilter(new OrderedEventChainFilter(new ActionEventChain(new BasicActionCommand(actionReceiver)))));
		
		chain.addEvent(EventCatalog.EVENT_A);
		chain.addEvent(EventCatalog.EVENT_B);
		chain.addEvent(EventCatalog.EVENT_C);
		
		chainChangeOrder1 = new ContinuousEventChainFilter(new CancellableEventChainFilter(new OrderedEventChainFilter(new ActionEventChain(new BasicActionCommand(actionReceiverChangeOrder1)))));
	
		chainChangeOrder1.addEvent(EventCatalog.EVENT_A);
		chainChangeOrder1.addEvent(EventCatalog.EVENT_B);
		chainChangeOrder1.addEvent(EventCatalog.EVENT_C);
		
		chainChangeOrder2 = new OrderedEventChainFilter(new ContinuousEventChainFilter(new CancellableEventChainFilter(new ActionEventChain(new BasicActionCommand(actionReceiverChangeOrder2)))));
		
		chainChangeOrder2.addEvent(EventCatalog.EVENT_A);
		chainChangeOrder2.addEvent(EventCatalog.EVENT_B);
		chainChangeOrder2.addEvent(EventCatalog.EVENT_C);

		EventManager.getInstance().registerEventChain(chain);
		EventManager.getInstance().registerEventChain(chainChangeOrder1);
		EventManager.getInstance().registerEventChain(chainChangeOrder2);
	}
	
	@Test
	public void testContinuousOrderedChainWithNoCancellations() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A-B-C y la accion no ocurrio!",actionReceiver.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A-B-C y la accion no ocurrio!",actionReceiverChangeOrder1.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A-B-C y la accion no ocurrio!",actionReceiverChangeOrder2.getState());
		
	}

	@Test
	public void testContinuousOrderedChainCancellationAndRepeat() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio AB y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio AB y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio AB y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABC y la accion no ocurrio!",actionReceiver.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABC y la accion no ocurrio!",actionReceiverChangeOrder1.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABC y la accion no ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK y la accion ocurrio!",actionReceiver.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK y la accion ocurrio!",actionReceiverChangeOrder1.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK y la accion ocurrio!",actionReceiverChangeOrder2.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK|A y la accion ya ocurrio!",actionReceiver.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK|A y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK|A y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK|AB y la accion ya ocurrio!",actionReceiver.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK|AB y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK|AB y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio ABCK|ABC y la accion no ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio ABCK|ABC y la accion no ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio ABCK|ABC y la accion no ocurrio!",actionReceiverChangeOrder2.getState());

	}

	
	
	@Test
	public void testContinuousUnOrderedChainWithNoCancellations() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio AC y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio AC y la accion ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio AC y la accion ocurrio!",actionReceiverChangeOrder2.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio ACB y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio ACB y la accion ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio ACB y la accion ocurrio!",actionReceiverChangeOrder2.getState());

	}

	
	
	@Test
	public void testDiscontinuousUnorderedChainWithOneCancellation() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-K y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-K y la accion ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-K y la accion ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-K-A y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-K-A y la accion ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-K-A y la accion ocurrio!",actionReceiverChangeOrder2.getState());
		
	}

	@Test
	public void testContinuousUnorderedChainWithCancellationAtFirst() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio K y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio K y la accion ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio K y la accion ocurrio!",actionReceiverChangeOrder2.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KB y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KB y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KB y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KBC y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KBC y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KBC y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KBCA y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KBCA y la accion ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KBCA y la accion ocurrio!",actionReceiverChangeOrder2.getState());
		
	}
	
	@Test
	public void testDiscontinuousUnorderedChainWithIrrelevantCancellers() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_J);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio J y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio J y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio J y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JB y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JB y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JB y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBC y la accion ya ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBC y la accion ya ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBC y la accion ya ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_L);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCL y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCL y la accion ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCL y la accion ocurrio!",actionReceiverChangeOrder2.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCLA y la accion ocurrio!",actionReceiver.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCLA y la accion ocurrio!",actionReceiverChangeOrder1.getState());
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCLA y la accion ocurrio!",actionReceiverChangeOrder2.getState());
		
	}

}
