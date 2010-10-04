package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import mat7510.eventManagerApi.ActionCommand;

public class ApagarBombaCmd implements ActionCommand {

	private BombaDeAgua bomba;
	
	public ApagarBombaCmd(BombaDeAgua b) {
		this.bomba = b;
	}

	public void execute() {
		bomba.apagar();
	}
}

