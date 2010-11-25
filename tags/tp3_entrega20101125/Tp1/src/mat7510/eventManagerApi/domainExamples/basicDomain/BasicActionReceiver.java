package mat7510.eventManagerApi.domainExamples.basicDomain;

public class BasicActionReceiver {
	
	private boolean state = false;

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean getState() {
		return state;
	}
	
}
