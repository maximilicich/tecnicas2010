/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.smartBuilding.gui.controller;

/**
 *
 * @author Grupo 10
 */
public class EventItem {
	private String eventID;
	private String driverID;
	public EventItem(String driverID,String eventID){
		this.eventID=eventID;
		this.driverID=driverID;
	}

	public String getDriverID(){ return driverID;}
	public String getEventID(){return eventID;}

	@Override
	public String toString() {
		return eventID+" ("+driverID+")";
	}
}