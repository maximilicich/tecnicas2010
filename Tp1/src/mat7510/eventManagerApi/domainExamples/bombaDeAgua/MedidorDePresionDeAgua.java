package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mat7510.eventManagerApi.Event;
import mat7510.eventManagerApi.EventListener;
import mat7510.eventManagerApi.exceptionRegisterEvent;

/**
 * Trabaja en PSI
 * @author MaMilicich
 *
 */
public class MedidorDePresionDeAgua {
	
	private List<EventListener> listeners = new ArrayList<EventListener>();

	/**
	 * indicador de cual es la presion minima en PSI
	 * Si la presion esta por debajo de este indice, 
	 * El medidor indicara que NO HAY PRESION
	 * 
	 * Valor default 100 PSI
	 */
	private BigDecimal presionMinima = new BigDecimal(100); 
	
	/**
	 * La red de abastecimiento a medir
	 */
	private RedDeAbastecimientoDeAgua red;
	
	public MedidorDePresionDeAgua(RedDeAbastecimientoDeAgua red) {
		this.red = red;
	}
	
	public void addEventListener(EventListener eventListener) {
		this.listeners.add(eventListener);
	}

	public void setPresionMinima(BigDecimal presionMinima) {
		this.presionMinima = presionMinima;
	}

	public BigDecimal getPresionMinima() {
		return presionMinima;
	}

	public Boolean hayPresion() {
		return (red.getPresionActual().compareTo(presionMinima) >= 0);
	}
	
	/**
	 * Cuando nos piden controlar la presion
	 * Nos fijamos cual es la presion de la red
	 * E informamos si hay presion o no, en base al indicador 
	 * de presionMinima
	 * @throws exceptionRegisterEvent 
	 */
	public void controlarPresion() throws exceptionRegisterEvent {
		Event e;
		if (hayPresion()) {
			e = new HayPresionEvent(this);
		} else {
			e = new NoHayPresionEvent(this);
		}
		for (EventListener listener : listeners) {
			listener.eventOccurred(e);
		}
	}


}
