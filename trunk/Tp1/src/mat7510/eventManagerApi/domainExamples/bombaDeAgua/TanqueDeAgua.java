package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import java.math.BigDecimal;

import mat7510.eventManagerApi.EventListener;

/**
 * Todas las cantidades son en LITROS
 * @author MaMilicich
 *
 */
public class TanqueDeAgua {

	private EventListener eventListener;

	private BigDecimal capacidadMaxima;
	private BigDecimal contenido;
	private BigDecimal limiteInferior;
	private BigDecimal limiteSuperior;
	
	public TanqueDeAgua(BigDecimal capacidadMaxima) {
		this(capacidadMaxima, BigDecimal.ZERO, capacidadMaxima);
	}

	public TanqueDeAgua(BigDecimal capacidadMaxima, 
						BigDecimal limiteInferior, 
						BigDecimal limiteSuperior) {
		// No se realizan validaciones en pos de la simplicidad (es un ejemplo)
		this.capacidadMaxima = capacidadMaxima;
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
	}
	
	public void addEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}

	public void llenar(BigDecimal litros) {
		contenido = 
			contenido.add(litros).compareTo(capacidadMaxima) > 0 ? 
					capacidadMaxima : contenido.add(litros);  
		if(isTanqueLleno()) {
			if (eventListener != null)
				eventListener.eventOccurred(new TanqueLlenoEvent(this));
		}
	}
	
	public void substractAgua(BigDecimal litros) {
		contenido = 
			contenido.subtract(litros).compareTo(BigDecimal.ZERO) < 0 ? 
					BigDecimal.ZERO : contenido.subtract(litros);  
		if(isTanqueVacio()) {
			if (eventListener != null)
				eventListener.eventOccurred(new TanqueVacioEvent(this));
		}
	}
	
	public boolean isTanqueLleno() {
		return contenido.compareTo(limiteSuperior) >= 0;
	}

	public boolean isTanqueVacio() {
		return contenido.compareTo(limiteInferior) <= 0;
	}

	public BigDecimal getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setLimiteInferior(BigDecimal limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public BigDecimal getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteSuperior(BigDecimal limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}

	public BigDecimal getLimiteSuperior() {
		return limiteSuperior;
	}

	public BigDecimal getContenido() {
		return contenido;
	}

}
