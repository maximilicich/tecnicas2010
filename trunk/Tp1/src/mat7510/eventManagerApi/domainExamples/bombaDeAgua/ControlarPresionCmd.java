package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import mat7510.eventManagerApi.ActionCommand;
import mat7510.eventManagerApi.exceptionRegisterEvent;

public class ControlarPresionCmd implements ActionCommand {

	private MedidorDePresionDeAgua medidor;
	
	public ControlarPresionCmd(MedidorDePresionDeAgua medidor) {
		this.medidor = medidor;
	}
	
	public void execute() throws exceptionRegisterEvent {
		this.medidor.controlarPresion();
	}

}
