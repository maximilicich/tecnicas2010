package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import mat7510.eventManagerApi.version1.ActionCommand;

public class ControlarPresionCmd implements ActionCommand {

	private MedidorDePresionDeAgua medidor;
	
	public ControlarPresionCmd(MedidorDePresionDeAgua medidor) {
		this.medidor = medidor;
	}
	
	public void execute() {
		this.medidor.controlarPresion();
	}

}
