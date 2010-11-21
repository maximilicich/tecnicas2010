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
	private Boolean enabled = true;
	private Boolean continuous = false;
	private Boolean ordered = false;
	
	public Rule(String ruleID, String ruleDescription, DeviceAction deviceAction) {
		super(deviceAction);
		this.ruleID = ruleID;
		this.ruleDescription = ruleDescription;
	}

	public String getRuleID() {
		return ruleID;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setContinuous(Boolean continuous) {
		this.continuous = continuous;
	}

	public Boolean isContinuous() {
		return continuous;
	}

	public void setOrdered(Boolean ordered) {
		this.ordered = ordered;
	}

	public Boolean isOrdered() {
		return ordered;
	}

}
