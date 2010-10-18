package mat7510.eventManagerApi.version2.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mat7510.eventManagerApi.version2.ActionEventChain;
import mat7510.eventManagerApi.version2.CancellableEventChainFilter;
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
	
	@Before
	public void setUp() {
		
		EventManager.getInstance().reset();
		EventManager.getInstance().setCancellableFor(new BasicEvent("K"), new BasicEvent("A"));
		EventManager.getInstance().setCancellableFor(new BasicEvent("K"), new BasicEvent("B"));
		EventManager.getInstance().setCancellableFor(new BasicEvent("K"), new BasicEvent("C"));
		EventManager.getInstance().setCancellableFor(new BasicEvent("H"), new BasicEvent("Y"));
		EventManager.getInstance().setCancellableFor(new BasicEvent("H"), new BasicEvent("A"));
		EventManager.getInstance().setCancellableFor(new BasicEvent("A"), new BasicEvent("J"));
		EventManager.getInstance().setCancellableFor(new BasicEvent("L"), new BasicEvent("K"));

		actionReceiver = new BasicActionReceiver();

		chain = new CancellableEventChainFilter(new ActionEventChain(new BasicActionCommand(actionReceiver)));
		
		chain.setContext(EventManager.getInstance());
		
		chain.addEvent(new BasicEvent("A"));
		chain.addEvent(new BasicEvent("B"));
		chain.addEvent(new BasicEvent("C"));

	}
	
	@Test
	public void testCancellableChainWithNoCancellations() {
		
		chain.eventOccurred(new BasicEvent("A"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio A-B y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio A-B-C y la accion no ocurrio!",actionReceiver.getState());
		
	}

	@Test
	public void testCancellableChainWithOneCancellation() {
		
		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("K"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-K y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("A"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio B-C-K-A y la accion ocurrio!",actionReceiver.getState());
		
	}

	@Test
	public void testCancellableChainWithCancellationAtFirst() {
		
		chain.eventOccurred(new BasicEvent("K"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio K y la accion ocurrio!",actionReceiver.getState());

		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KB y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio KBC y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("A"));
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio KBCA y la accion NO ocurrio!",actionReceiver.getState());
		
	}
	
	@Test
	public void testCancellableChainWithIrrelevantCancellers() {
		
		chain.eventOccurred(new BasicEvent("J"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio J y la accion ya ocurrio!",actionReceiver.getState());

		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JB y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBC y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("L"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCL y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("A"));
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio JBCLA y la accion NO ocurrio!",actionReceiver.getState());
		
	}

	
	@Test
	public void testCancellableChainRepeatAfterCancelling() {
		
		chain.eventOccurred(new BasicEvent("J"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio J y la accion ya ocurrio!",actionReceiver.getState());

		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JB y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("C"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBC y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("K"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCK y la accion ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("A"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCKA y la accion ocurrio!",actionReceiver.getState());

		// Ahora ocurren de nuevo B y C para que la accion se dispare
		// En el medio ocurren L y J que son irrelevantes
		
		chain.eventOccurred(new BasicEvent("J"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCKAJ y la accion ya ocurrio!",actionReceiver.getState());

		chain.eventOccurred(new BasicEvent("B"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCKAJB y la accion ya ocurrio!",actionReceiver.getState());
		
		chain.eventOccurred(new BasicEvent("L"));
		assertFalse(ASSERT_MSG_PREFIX + "ocurrio JBCKAJBL y la accion ya ocurrio!",actionReceiver.getState());

		chain.eventOccurred(new BasicEvent("C"));
		assertTrue(ASSERT_MSG_PREFIX + "ocurrio JBCKAJBLC y la accion NO ocurrio!",actionReceiver.getState());
		
	}

}
