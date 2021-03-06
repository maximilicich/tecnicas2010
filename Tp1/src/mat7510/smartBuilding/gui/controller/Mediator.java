/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.smartBuilding.gui.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mat7510.smartBuilding.domain.Rule;
import mat7510.smartBuilding.domain.devicedriver.DeviceAction;
import mat7510.smartBuilding.domain.devicedriver.DeviceEvent;
import mat7510.smartBuilding.exception.SmartBuildingException;
import mat7510.smartBuilding.gui.view.ContentPanel;
import mat7510.smartBuilding.gui.view.DescriptionRuleDialog;
import mat7510.smartBuilding.gui.view.ListPanel;
import mat7510.smartBuilding.gui.view.MainFrame;
import mat7510.smartBuilding.gui.view.NewRuleDialog;
import mat7510.smartBuilding.service.SmartBuildingManager;

/**
 *
 * @author Grupo 10
 */
public class Mediator {
	MainFrame mainFrame;
	ListPanel driversListPanel;
	ListPanel stateListPanel;
	ListPanel actionListPanel;
	ListPanel eventListPanel;
	ListPanel rulesListPanel;
	Translator translator;


	public Mediator(SmartBuildingManager buildingManager){
		mainFrame = new MainFrame();
		translator = new Translator(buildingManager);
		createWindow();
		try {
			driversListPanel.addAll(translator.getDriverIds().iterator());
		} catch (SmartBuildingException ex) {}
	}

	public JFrame getWindows(){
		return mainFrame;
	}
	public void addDriverList(ListPanel list){
		driversListPanel=list;
	}

	public void addStateList(ListPanel list){
		stateListPanel=list;
	}

	public void addActionList(ListPanel list){
		actionListPanel=list;
	}

	public void addEventList(ListPanel list){
		eventListPanel=list;
	}

	public void addRuleList(ListPanel list){
		rulesListPanel=list;
	}

	private void createWindow(){
		ContentPanel contentPane = new ContentPanel(this);
		mainFrame.getContentPane().add(contentPane);
	}

	public void showWindow(){
		mainFrame.setVisible(true);
	}

        public void reload(){
            try {
                translator.reload();
                driversListPanel.clear();
                clearDriver();
                driversListPanel.addAll(translator.getDriverIds().iterator());
            } catch (SmartBuildingException ex) {
               JOptionPane.showMessageDialog(mainFrame, "Error, al recargar los datos.", "", JOptionPane.ERROR_MESSAGE);
            }
        }
        
	private void clearDriver(){
		actionListPanel.clear();
		stateListPanel.clear();
		eventListPanel.clear();
		rulesListPanel.clear();
	}

	public void selectDriverWithIndex(String deviceID){
		clearDriver();
		try {
			stateListPanel.addAll(translator.getDriverStates(deviceID).iterator());
			actionListPanel.addAll(translator.getDriverActionsIds(deviceID).iterator());
			eventListPanel.addAll(translator.getDriverEventsIds(deviceID).iterator());
		} catch (SmartBuildingException ex) {
			JOptionPane.showMessageDialog(mainFrame, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void selectActionWithIndex(String actionID){
		String driverID = (String) driversListPanel.getSelectedValue();

		try {
			if(actionID==null || driverID==null || actionID.equals("") || driverID.equals("")){
				JOptionPane.showMessageDialog(mainFrame ,"Driver o accion no seleccionada","Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			rulesListPanel.clear();
			List<String> rules = translator.getRulesForDeviceAction(driverID, actionID);
			if(!rules.isEmpty()){               
				rulesListPanel.addAll(rules.iterator());
			}

		} catch (SmartBuildingException ex) {
			JOptionPane.showMessageDialog(mainFrame, ex, actionID, JOptionPane.ERROR_MESSAGE);
		}
	}

	public void update(){
		try {
			clearDriver();
			driversListPanel.clear();
			translator.reload();
			driversListPanel.addAll(translator.getDriverIds().iterator());
		} catch (SmartBuildingException ex) {
			JOptionPane.showMessageDialog(mainFrame , ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void updateRulePanel() {

		String driverID = (String) driversListPanel.getSelectedValue();
		String actionID = (String) actionListPanel.getSelectedValue();

		try {
			if(actionID==null || driverID==null || actionID.equals("") || driverID.equals("")){
				JOptionPane.showMessageDialog(mainFrame ,"Driver o accion no seleccionada","Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			rulesListPanel.clear();
			List<String> rules = translator.getRulesForDeviceAction(driverID, actionID);
			if(!rules.isEmpty()){               
				rulesListPanel.addAll(rules.iterator());
			}
		} catch (SmartBuildingException ex) {
			JOptionPane.showMessageDialog(mainFrame , ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	public void executeActionWithIndex(String actionID){
		String driverID = (String) driversListPanel.getSelectedValue();
		try {
			if(actionID==null || driverID==null || actionID.equals("") || driverID.equals("")){
				JOptionPane.showMessageDialog(mainFrame ,"Driver o accion no seleccionada","Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			translator.execute(driverID, actionID);
			stateListPanel.refresh();
			stateListPanel.addAll(translator.getDriverStates(driverID).iterator());
		} catch (SmartBuildingException ex) {
			JOptionPane.showMessageDialog(mainFrame, ex, actionID, JOptionPane.ERROR_MESSAGE);
		}
	}

	public void removeRuleWithIndex(String ruleID){
		try {
			translator.deleteRule(ruleID);
		} catch (SmartBuildingException ex) {
			JOptionPane.showMessageDialog(mainFrame, "Error al eliminar la regla: "+ruleID, "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void addNewRule(NewRuleDialog dialog){

		try {

			String driverID = (String) driversListPanel.getSelectedValue();

			DeviceAction action = translator.getDeviceAction(driverID, dialog.getNameAction());

			Rule newRule = new Rule.Builder(dialog.getNameRule(),dialog.getDescriptionRule()).
										continuous(dialog.isContinuos()).
										ordered(dialog.isOrder()).
										action(action).
										build();

			for(EventItem event : dialog.getEvents()){
				DeviceEvent e = translator.getDeviceEvent(event.getDriverID(), event.getEventID());
				newRule.addDeviceEvent(e);
			}

			translator.addRule(newRule);
		} catch (SmartBuildingException ex) {
			JOptionPane.showMessageDialog(mainFrame, "Error al crear la regla", "", JOptionPane.ERROR_MESSAGE);
		}

		dialog.dispose();
		this.updateRulePanel();
	}

	public void createNewRule(){
		String driverID = (String) driversListPanel.getSelectedValue();

		if(driverID==null){
			JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar un driver");
			return;
		}
		new NewRuleDialog(mainFrame,this);
	}

	public void showDescriptionRule(String ruleID){
		try {
			Rule rule = translator.getRule(ruleID);
			String nameAction = rule.getDeviceAction().getActionName();

			ArrayList<String> arrayOfEvents = translator.getListOfRuleEvents(rule);
			ArrayList<String> arrayOfTypes = translator.getListOfRuleType(rule);

			String typeText="";
			for(String text :arrayOfTypes ){
				typeText+=text+"/";
			}

			typeText= typeText.substring(0, typeText.length()-1);
			new DescriptionRuleDialog(mainFrame,ruleID,nameAction,typeText,arrayOfEvents,rule.isEnabled(),rule.getRuleDescription());

		} catch (SmartBuildingException ex) {
			JOptionPane.showMessageDialog(mainFrame, "Error al tratar de mostrar la información", "", JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<String> getDriversId() throws SmartBuildingException{
		return translator.getDriverIds();
	}

	public ArrayList<String> getEventsForDriverID(String deviceID) throws SmartBuildingException{
		return translator.getDriverEventsIds(deviceID);
	}

	public ArrayList<String> getAction() throws SmartBuildingException{
		String driverID = (String) driversListPanel.getSelectedValue();
		return translator.getDriverActionsIds(driverID);
	}

	public void showEditRule(String ruleID){
		Rule rule;
		try {
			rule = translator.getRule(ruleID);


			if(rule.isEnabled()){
				int n = JOptionPane.showConfirmDialog(mainFrame, "La regla se encuentra Habilitada, Desea deshabilitarla?","Habilitar/Desabilitar Regla",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					translator.disableRule(ruleID);
				}

			}else{
				int n = JOptionPane.showConfirmDialog(mainFrame, "La regla se encuentra Deshabilitada, Desea habilitarla?","Habilitar/Desabilitar Regla",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					translator.enableRule(ruleID);
				}
			}
		} catch (SmartBuildingException ex) {
			JOptionPane.showMessageDialog(mainFrame, "Error al tratar de cambiar el estado", "", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void addDriverWithName(String dir){

		// JOptionPane.showMessageDialog(mainFrame, "url: "+dir);
		try {
			translator.addDeviceDriver(dir);
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(mainFrame, ex, "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SmartBuildingException e) {
			JOptionPane.showMessageDialog(mainFrame, e, "Error", JOptionPane.ERROR_MESSAGE);
		}

		this.update();

	}
}
