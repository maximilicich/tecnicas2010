package mat7510.smartBuilding.domain;

import java.util.ArrayList;
import java.util.List;

import mat7510.eventManagerApi.version2.ActionCommand;
import mat7510.eventManagerApi.version2.ActionEventChain;
import mat7510.eventManagerApi.version2.ContinuousEventChainFilter;
import mat7510.eventManagerApi.version2.EventChain;
import mat7510.eventManagerApi.version2.OrderedEventChainFilter;
import mat7510.smartBuilding.domain.devicedriver.DeviceAction;
import mat7510.smartBuilding.domain.devicedriver.DeviceEvent;

/**
 * 
 * Una regla es la relacion entre una serie de eventos informados por Devies
 * y una Accion a realizar por un Device
 * 
 * 
 * @author Grupo 10 
 *
 */
public class Rule {

	private String ruleID;
	private String ruleDescription;
	private Boolean enabled = true;
	private Boolean continuous = false;
	private Boolean ordered = false;
	
	private ActionEventChain actionEventChain;
	private EventChain decoratedEventChain;
	
	private DeviceAction deviceAction;
	
	private List<DeviceEvent> deviceEvents = new ArrayList<DeviceEvent>();
	
	
	/**
	 * El Constructor ES PRIVADO
	 * Ya que para crear una Regla se usa el BUILDER provisto por esta
	 * misma Clase
	 * 
	 * @param ruleID
	 * @param ruleDescription
	 */
	private Rule(String ruleID, String ruleDescription) {
		if (ruleID == null)
			throw new IllegalArgumentException("Cannot instantiate Rule with null ruleID");
		if (ruleID.trim().equalsIgnoreCase(""))
			throw new IllegalArgumentException("Cannot instantiate Rule with blank ruleID");
		
		this.ruleID = ruleID;
		this.ruleDescription = ruleDescription;
	}

	/**
	 * Un Builder para la Rule 
	 * 
	 * Los atributos minimos obligatorios son
	 * - ruleID 
	 * - ruleDescription
	 * 
	 * Asi, si queremos crear una Rule solo con estos parms, hacemos
	 * 
	 * new Rule.Builder(ruleID, ruleDesctiption).build();
	 * 
	 * Luego tenemos parms adicionales, opcionales:
	 * - continuous (es una Regla de eventos continuos ?)
	 * - ordered (es una Regla de eventos ordenados ?)
	 * - La DeviceAction asociada!
	 * 
	 * Suponiendo que queremos crear una Rule
	 * ordenada
	 * continua
	 * y con el DeviceAction miAction:
	 * 
	 * Para esto, hacemos
	 * 
	 * new Rule.Builder(ruleID, ruleDesctiption).continuous(true).ordered(true).action(miACtion).build();
	 * 
	 * Segun pattern de Joshua Bloch
	 * http://rwhansen.blogspot.com/2007/07/theres-builder-pattern-that-joshua.html
	 * http://www.drdobbs.com/java/208403883;jsessionid=KP2YOIG0O2LWFQE1GHOSKH4ATMY32JVN?pgno=2
	 * 
	 * @author MA_Xx
	 *
	 */
	public static class Builder {

		private String ruleID;
		private String ruleDescription;
		private Boolean continuous = false;
		private Boolean ordered = false;
		
		private DeviceAction deviceAction;
		
		public Builder(String ruleID, String ruleDescription) {
			this.ruleID = ruleID;
			this.ruleDescription = ruleDescription;
		}
		
		public Rule build() {
			
			Rule rule = new Rule(ruleID, ruleDescription);
			
			rule.continuous = continuous;
			rule.ordered = ordered;
			rule.deviceAction = deviceAction;
			
			ActionCommand action;
			if (deviceAction != null)
				action = deviceAction;
			else
				action = new VoidAction();
			
			rule.actionEventChain = new ActionEventChain(action); 
			
			EventChain chain = rule.actionEventChain;
			
			// Recursive Object Composition! 
			if(ordered)
				chain = new OrderedEventChainFilter(chain);

			// Recursive Object Composition! 
			if(continuous)
				chain = new ContinuousEventChainFilter(chain);

			rule.decoratedEventChain = chain;
			
			return rule;
		}
		
		
		public Builder action(DeviceAction deviceAction) {
			this.deviceAction = deviceAction;
			return this;
		}
		
		public Builder continuous(Boolean isContinuous) {
			this.continuous = isContinuous;
			return this;
		}
		
		public Builder ordered(Boolean isOrdered) {
			this.ordered = isOrdered;
			return this;
		}
	} // class Builder
	
	
	public String getRuleID() {
		return ruleID;
	}
	
	public void setRuleID( String ID) {
		ruleID = ID;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
		// Switch de la Action segun activemos o inactivemos la Regla
		if (enabled && this.deviceAction != null) {
			this.actionEventChain.setAction(deviceAction);
		}
		if (! enabled) {
			this.actionEventChain.setAction(new VoidAction());
		}
	}

	public Boolean isEnabled() {
		return enabled;
	}


	public Boolean isContinuous() {
		return continuous;
	}


	public Boolean isOrdered() {
		return ordered;
	}

	public EventChain getEventChain() {
		return decoratedEventChain;
	}

	
	public void setDeviceAction(DeviceAction deviceAction) {
		this.deviceAction = deviceAction;
		this.actionEventChain.setAction(deviceAction);
	}

	public DeviceAction getDeviceAction() {
		return deviceAction;
	}

	public void addDeviceEvent(DeviceEvent deviceEvent) {
		if (deviceEvent == null)
			throw new IllegalArgumentException("Cannot add a null deviceEvent to a Rule");
		this.deviceEvents.add(deviceEvent);
		this.getEventChain().addEvent(deviceEvent);
	}
	
	public List<DeviceEvent> getDeviceEvents() {
		return this.deviceEvents;
	}
	
	/**
	 * Un Rule es igual a otro si y solo si sus ID son iguales
	 */
	public boolean equals(Object obj) {
		
		if (!( obj instanceof Rule))
			return false;
		
		Rule anotherRule = (Rule)obj;
		
		if (this.ruleID == null || anotherRule.ruleID == null)
			return false;
		
		return this.ruleID.trim().equalsIgnoreCase(anotherRule.ruleID.trim());
	}

	
	@Override
	public int hashCode() {
		return this.ruleID.hashCode();
	}
	
	@Override
	public String toString() {
		return "Rule ID : " + ruleID + " | description : " + ruleDescription;
	}
	

	/**
	 * Un ActionEventChain necesita siempre una Action
	 * Pero la Rule podria no tener Action definida en un primer momento
	 * Entonces esta accion suplementa el rol de ActionCommand para el Chain
	 * Hasta que aparece la DeviceAction
	 * Tambien esta Action se usa para inactivar la Regla:
	 * Regla inactiva es la que su Chain no hace nada
	 * 
	 * @author MA_Xx
	 *
	 */
	static class VoidAction implements ActionCommand {

		@Override
		public void execute() {
			// NO HACE NADA, PUES ES VOID ACTION
		}
	}
	
}
