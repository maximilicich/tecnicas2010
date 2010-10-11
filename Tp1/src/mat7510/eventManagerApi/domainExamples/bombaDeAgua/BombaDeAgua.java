package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

public class BombaDeAgua {

	private Boolean estado = false; 
	
	public void encender() {
		estado = true;
	}

	public void apagar() {
		estado = false;
	}

	public Boolean isEncendida() {
		return estado;
	}

}
