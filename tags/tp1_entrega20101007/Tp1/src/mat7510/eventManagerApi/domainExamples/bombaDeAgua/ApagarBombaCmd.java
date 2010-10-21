package mat7510.eventManagerApi.domainExamples.bombaDeAgua;


public class ApagarBombaCmd extends BombaCmd {

	public ApagarBombaCmd(BombaDeAgua b) {
		super(b);
	}

	public void execute() {
		bomba.apagar();
	}
}

