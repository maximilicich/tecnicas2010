package mat7510.eventManagerApi.domainExamples.bombaDeAgua;

public class EncenderBombaCmd extends BombaCmd {

	public EncenderBombaCmd(BombaDeAgua b) {
		super(b);
	}

	public void execute() {
		bomba.encender();
	}
}
