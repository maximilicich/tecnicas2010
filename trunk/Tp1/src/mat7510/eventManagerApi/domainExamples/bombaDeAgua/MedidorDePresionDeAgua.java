package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mat7510.eventManagerApi.Event;
import mat7510.eventManagerApi.EventListener;

/**
 * Trabaja en PSI
 * @author MaMilicich
 *
 */
public class MedidorDePresionDeAgua implements EventListener  {
	
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
		this.red.addListener(this);
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
	
	/**
	 * Cuando nos piden controlar la presion
	 * Nos fijamos cual es la presion de la red
	 * E informamos si hay presion o no, en base al indicador 
	 * de presionMinima
	 */
	public void controlarPresion() {
		Event e;
		if (red.getPresionActual().compareTo(presionMinima) < 0) {
			e = new NoHayPresionEvent(this);
		} else {
			e = new HayPresionEvent(this);
		}
		for (EventListener listener : listeners) {
			listener.eventOccurred(e);
		}
	}

	@Override
	public void eventOccurred(Event e) {
		// TODO Auto-generated method stub
	}
	
}
