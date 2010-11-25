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
 * Aplicamos solo un filtro CAncellable sobre un EventChain ABC-
 * Tener en cuenta que por default el EventChain
 * es Discontinuo y No Ordenado
 * 
 * K cancela a A, a B y a C
 * H cancela a A
 * 
 * @author Grupo 10 
 *
 */
public class CancellableChainFilterTest {

	private BasicActionReceiver actionReceiver;
	private EventChain chain;
	private final String ASSERT_MSG_PREFIX = "[A-B-C Continuo] ";
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

		chain = actionEventChainFactory.createCancellableChain(new BasicActionCommand(actionReceiver), eventList);
		
		EventManager.getInstance().registerEventChain(chain);
	}
	
	@Test
	public void testCancellableChainWithNoCancellations() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A-B-C y la accion no ocurrio!",actionReceiver.getState());
		
	}

	@Test
	public void testCancellableChainWithOneCancellation() {
		
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
	public void testCancellableChainWithCancellationAtFirst() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio K y la accion ocurrio!",actionReceiver.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KB y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KBC y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio KBCA y la accion NO ocurrio!",actionReceiver.getState());
		
	}
	
	@Test
	public void testCancellableChainWithIrrelevantCancellers() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_J);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio J y la accion ya ocurrio!",actionReceiver.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JB y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBC y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_L);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCL y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio JBCLA y la accion NO ocurrio!",actionReceiver.getState());
		
	}

	
	@Test
	public void testCancellableChainRepeatAfterCancelling() {
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_J);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio J y la accion ya ocurrio!",actionReceiver.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JB y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBC y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_K);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCK y la accion ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_A);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCKA y la accion ocurrio!",actionReceiver.getState());

		// Ahora ocurren de nuevo B y C para que la accion se dispare
		// En el medio ocurren L y J que son irrelevantes
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_J);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCKAJ y la accion ya ocurrio!",actionReceiver.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_B);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCKAJB y la accion ya ocurrio!",actionReceiver.getState());
		
		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_L);
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCKAJBL y la accion ya ocurrio!",actionReceiver.getState());

		EventManager.getInstance().eventOccurred(EventCatalog.EVENT_C);
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio JBCKAJBLC y la accion NO ocurrio!",actionReceiver.getState());
		
	}

}
