package mat7510.eventManager;

public class EncenderBombaCmd extends BombaCmd {

	public EncenderBombaCmd(BombaDeAgua b) {
		super(b);
	}

	public void execute() {
		bomba.encender();
	}
}
