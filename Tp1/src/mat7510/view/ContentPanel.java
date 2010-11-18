/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author sergio
 */
public class ContentPanel extends JPanel implements ActionListener,ListSelectionListener{
    private Mediador mediador;

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
        updateButton.setActionCommand("update");
        updateButton.addActionListener(this);

        ListPanel driversListPanel = new ListPanel(mediador);
        mediador.addDriverList(driversListPanel);
        driversListPanel.addListSelectionListener(this);

        JPanel controllerPanel = createControllerPanel();
        controllerPanel.add(updateButton);

        JPanel driverPanel = createContentPanel();
        driverPanel.add(driversListPanel);
        driverPanel.add(controllerPanel);

        return driverPanel;
    }

    private JPanel createStatePanel(){
        JPanel statePanel = createContentPanel();
        ListPanel stateListPanel = new ListPanel(mediador);
        mediador.addStateList(stateListPanel);
        
        statePanel.add(stateListPanel);
        JPanel controllerPanel = createControllerPanel();
        statePanel.add(controllerPanel);
        return statePanel;
    }

    private JPanel createActionPanel(){
        ListPanel actionListPanel = new ListPanel(mediador);
        mediador.addActionList(actionListPanel);

        JButton executeButton = new JButton("Ejecutar");
        executeButton.setActionCommand("execute");
        executeButton.addActionListener(this);

        JPanel controlerPanel = createControllerPanel();
        controlerPanel.add(executeButton);

         JPanel actionPanel = createContentPanel();
        actionPanel.add(actionListPanel);
        actionPanel.add(controlerPanel);
        return actionPanel;
    }

    private JPanel createEventPanel(){
        JButton deleteButton = new JButton("Eliminar");
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(this);

        JButton addButton = new JButton("Agregar");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);

        JButton detailButton = new JButton("Descripcion");
        detailButton.setActionCommand("description");
        detailButton.addActionListener(this);

        JPanel controllerEventPanel = createControllerPanel();
        controllerEventPanel.add(addButton);
        controllerEventPanel.add(deleteButton);
        controllerEventPanel.add(detailButton);

       ListPanel eventListPanel = new ListPanel(mediador);
       mediador.addEventList(eventListPanel);
       
        JPanel eventPanel = createContentPanel();
        eventPanel.add(eventListPanel);
        eventPanel.add(controllerEventPanel);
        return eventPanel;
    }

    public ContentPanel(Mediador med){
        mediador=med;        
        setLayout(new BorderLayout());

         JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 4));
        centerPanel.add(createDriverPanel());
        centerPanel.add(createStatePanel());
        centerPanel.add(createActionPanel());
        centerPanel.add(createEventPanel());
        add(centerPanel, BorderLayout.CENTER);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4,5,25));
        topPanel.add(new JLabel("Drivers", JLabel.CENTER));
        topPanel.add(new JLabel("Estados", JLabel.CENTER));
        topPanel.add(new JLabel("Acciones", JLabel.CENTER));
        topPanel.add(new JLabel("Eventos", JLabel.CENTER));
        this.add(topPanel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent e) {

        JOptionPane.showMessageDialog(this, "boton: "+e.getActionCommand());

        if (e.getActionCommand()=="add" ){

        }else if (e.getActionCommand()=="delete" ){

        }else if (e.getActionCommand()=="update" ){

        }else if (e.getActionCommand()=="description" ){

        }else if (e.getActionCommand()=="execute" ){

        }

    }

    public void valueChanged(ListSelectionEvent e) {
        //capturo las selecciones de la lista de drivers
    }


}
