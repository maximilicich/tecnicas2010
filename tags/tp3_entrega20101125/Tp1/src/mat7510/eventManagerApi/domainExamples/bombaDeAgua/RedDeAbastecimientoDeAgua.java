package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mat7510.eventManagerApi.version1.EventListener;

/**
 * SINGLETON
 * Presion medida en PSI (0 - 100)
 * @author MaMilicich
 *
 */
public class RedDeAbastecimientoDeAgua {

	/**
	 * La instancia SINGLETON
	 */
	private static RedDeAbastecimientoDeAgua instance = new RedDeAbastecimientoDeAgua ();

	/**
	 * Los listeners (clientes de la red)
	 */
	private List<EventListener> listeners = new ArrayList<EventListener>();
	
	/**
	 * La presion actual de la Red
	 * Se setea por afuera
	 */
	private BigDecimal presionActual = BigDecimal.ZERO;

	private RedDeAbastecimientoDeAgua() {
	}

	public static RedDeAbastecimientoDeAgua instance() {
		return instance;
	}
	
	public void addListener(EventListener listener) {
		this.listeners.add(listener);
	}

	public void setPresionActual(BigDecimal presionActual) {
		this.presionActual = presionActual;
		for (Iterator<EventListener> iterator = listeners.iterator(); iterator.hasNext();) {
			EventListener listener = iterator.next();
			listener.eventOccurred(new CambioDePresionDeAguaEvent());
		}
	}

	public BigDecimal getPresionActual() {
		return presionActual;
	}
	
	
}
