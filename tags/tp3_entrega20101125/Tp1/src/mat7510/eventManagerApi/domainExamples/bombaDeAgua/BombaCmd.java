package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

import mat7510.eventManagerApi.version1.ActionCommand;

public abstract class BombaCmd implements ActionCommand {

	protected BombaDeAgua bomba;
	
	public BombaCmd(BombaDeAgua b) {
		this.bomba = b;
	}

}
