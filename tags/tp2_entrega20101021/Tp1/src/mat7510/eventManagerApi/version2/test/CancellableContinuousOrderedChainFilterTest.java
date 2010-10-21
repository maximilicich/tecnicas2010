package mat7510.eventManagerApi.version2.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import mat7510.eventManagerApi.version2.ActionEventChainFactory;
import mat7510.eventManagerApi.version2.Event;
import mat7510.eventManagerApi.version2.EventChain;
import mat7510.eventManagerApi.version2.EventManager;
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
	private EventChain chain;
	private final String ASSERT_MSG_PREFIX = "[A-B-C Cancelable Continuo y Ordenado] ";
	private ActionEventChainFactory actionEventChainFactory;

	
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

		List <Event> eventList =new ArrayList<Event>();
		
		eventList.add(EventCatalog.EVENT_A);
		eventList.add(EventCatalog.EVENT_B);
		eventList.add(EventCatalog.EVENT_C);
		
		actionReceiver = new BasicActionReceiver();
		actionEventChainFactory = ActionEventChainFactory.getInstance();

		chain = actionEventChainFactory.createCancellableContinuosOrderedChain(new BasicActionCommand(actionReceiver), eventList);

		EventManager.getInstance().registerEventChain(chain);
	}
	
	@Test
	public void testContinuousOrderedChainWithNoCancellations() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A-B-C y la accion no ocurrio!",actionReceiver.getState());
		
	}

	@Test
	public void testContinuousOrderedChainCancellationAndRepeat() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio AB y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABC y la accion no ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK y la accion ocurrio!",actionReceiver.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK|A y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio ABCK|AB y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio ABCK|ABC y la accion no ocurrio!",actionReceiver.getState());

	}

	
	
	@Test
	public void testContinuousUnOrderedChainWithNoCancellations() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio AC y la accion ocurrio!",actionReceiver.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio ACB y la accion ocurrio!",actionReceiver.getState());

	}

	
	
	@Test
	public void testDiscontinuousUnorderedChainWithOneCancellation() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-K y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-K-A y la accion ocurrio!",actionReceiver.getState());
		
	}

	@Test
	public void testContinuousUnorderedChainWithCancellationAtFirst() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio K y la accion ocurrio!",actionReceiver.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KB y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KBC y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KBCA y la accion ocurrio!",actionReceiver.getState());
		
	}
	
	@Test
	public void testDiscontinuousUnorderedChainWithIrrelevantCancellers() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_J);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio J y la accion ya ocurrio!",actionReceiver.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JB y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBC y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_L);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCL y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCLA y la accion ocurrio!",actionReceiver.getState());
		
	}

}
