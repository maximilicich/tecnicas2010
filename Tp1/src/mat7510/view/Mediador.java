/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author sergio
 */
public class Mediador {
    MainFrame mainFrame;
    ListPanel driversListPanel;
    ListPanel stateListPanel;
    ListPanel actionListPanel;
    ListPanel eventListPanel;

    public Mediador(){
        mainFrame = new MainFrame();
        createWindow();
    }

    private JPanel createContentPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(5,5,5,5));
        return panel;
    }

    private JPanel createControllerPanel(){
        JPanel controllerPanel = new JPanel();
        controllerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        controllerPanel.setMaximumSize(new Dimension(400,20));
        controllerPanel.setPreferredSize(new Dimension(400,45));
        return controllerPanel;
    }

    private JPanel createDriverPanel(){
        JButton updateButton = new JButton("Actualizar");
        driversListPanel = new ListPanel(this);
        JPanel controllerPanel = createControllerPanel();
        controllerPanel.add(updateButton);

        JPanel driverPanel = createContentPanel();
        driverPanel.add(driversListPanel);
        driverPanel.add(controllerPanel);

        return driverPanel;
    }

    private JPanel createStatePanel(){
        JPanel statePanel = createContentPanel();
        stateListPanel = new ListPanel(this);
        statePanel.add(stateListPanel);
        JPanel controllerPanel = createControllerPanel();        
        statePanel.add(controllerPanel);
        return statePanel;
    }

    private JPanel createActionPanel(){       
        actionListPanel = new ListPanel(this);
        JButton executeButton = new JButton("Ejecutar");
        JPanel controlerPanel = createControllerPanel();
        controlerPanel.add(executeButton);

         JPanel actionPanel = createContentPanel();
        actionPanel.add(actionListPanel);
        actionPanel.add(controlerPanel);
        return actionPanel;
    }

    private JPanel createEventPanel(){
        JButton deleteButton = new JButton("Eliminar");
        JButton addButton = new JButton("Agregar");
        JButton detailButton = new JButton("Descripcion");

        JPanel controllerEventPanel = createControllerPanel();
        controllerEventPanel.add(addButton);
        controllerEventPanel.add(deleteButton);
        controllerEventPanel.add(detailButton);

        eventListPanel = new ListPanel(this);

        JPanel eventPanel = createContentPanel();
        eventPanel.add(eventListPanel);
        eventPanel.add(controllerEventPanel);
        return eventPanel;
    }

    private void createWindow(){

        JPanel contentPane = new JPanel();
        mainFrame.getContentPane().add(contentPane);
        contentPane.setLayout(new BorderLayout());

         JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 4));
        centerPanel.add(createDriverPanel());
        centerPanel.add(createStatePanel());
        centerPanel.add(createActionPanel());
        centerPanel.add(createEventPanel());
        contentPane.add(centerPanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4,5,25));
        topPanel.add(new JLabel("Drivers", JLabel.CENTER));
        topPanel.add(new JLabel("Estados", JLabel.CENTER));
        topPanel.add(new JLabel("Acciones", JLabel.CENTER));
        topPanel.add(new JLabel("Eventos", JLabel.CENTER));
        contentPane.add(topPanel, BorderLayout.NORTH);

    }
    
    public void showWindow(){
        mainFrame.setVisible(true);
    }

}
