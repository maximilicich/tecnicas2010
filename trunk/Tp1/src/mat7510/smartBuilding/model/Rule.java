package mat7510.smartBuilding.model;

import mat7510.eventManagerApi.version2.ActionEventChain;

/**
 * 
 * Una regla es la relacion entre una serie de eventos informados por Devies
 * y una Accion a realizar por un Device
 * 
 * 
 * @author Grupo 10 
 *
 */
public class Rule extends ActionEventChain {

	private String ruleID;
	private String ruleDescription;

	public Rule(String ruleID, String ruleDescription, DeviceAction action) {
		super(action);
		this.ruleID = ruleID;
		this.ruleDescription = ruleDescription;
	}

	public String getRuleID() {
		return ruleID;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

}
