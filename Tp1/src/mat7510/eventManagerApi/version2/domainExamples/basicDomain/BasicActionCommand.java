package mat7510.eventManagerApi.version2.domainExamples.basicDomain;

import mat7510.eventManagerApi.version2.ActionCommand;

public class BasicActionCommand implements ActionCommand {

	BasicActionReceiver receiver;

	public BasicActionCommand(BasicActionReceiver receiver) {
		this.receiver = receiver;
	}

	public void execute() {
		receiver.setState(!(receiver.getState()));
	}

}
