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

    public Mediador(){
        mainFrame = new MainFrame();
        createWindow();
    }

    private void createWindow(){

        JPanel contentPane = new JPanel();
        mainFrame.getContentPane().add(contentPane);
        contentPane.setLayout(new BorderLayout());

        JButton updateButton = new JButton("Actualizar");
        JPanel driversListPanel = new JPanel();

        JPanel driverPanel = new JPanel();
        driverPanel.setBorder(new EmptyBorder(5,5,5,5));
        driverPanel.setLayout(new BoxLayout(driverPanel, BoxLayout.Y_AXIS));
        driverPanel.add(driversListPanel);
        driverPanel.add(updateButton);

        JPanel statePanel = new JPanel();
        statePanel.setBorder(new EmptyBorder(5,5,5,5));

        JButton executeButton = new JButton("Ejecutar");
        JPanel actionListPanel = new JPanel();

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setBorder(new EmptyBorder(5,5,5,5));
        actionPanel.add(actionListPanel);
        actionPanel.add(executeButton);

        
        JPanel eventListPanel = new JPanel();
       

        JButton deleteButton = new JButton("Eliminar");
        JButton addButton = new JButton("Agregar");
        JButton detailButton = new JButton("Descripcion");

        JPanel controlerEventPanel = new JPanel();
        controlerEventPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        controlerEventPanel.setMaximumSize(new Dimension(500,20));

        controlerEventPanel.add(addButton);
        controlerEventPanel.add(deleteButton);
        controlerEventPanel.add(detailButton);
        

        JPanel eventPanel = new JPanel();
        eventPanel.setBorder(new EmptyBorder(5,5,5,5));
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
        eventPanel.add(eventListPanel);
        eventPanel.add(controlerEventPanel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 4));
        centerPanel.add(driverPanel);
        centerPanel.add(statePanel);
        centerPanel.add(actionPanel);
        centerPanel.add(eventPanel);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4));
        topPanel.add(new JLabel("Drivers", JLabel.CENTER));
        topPanel.add(new JLabel("Estados", JLabel.CENTER));
        topPanel.add(new JLabel("Acciones", JLabel.CENTER));
        topPanel.add(new JLabel("Eventos", JLabel.CENTER));
        contentPane.add(topPanel, BorderLayout.NORTH);

        topPanel.setBackground(Color.red);
        controlerEventPanel.setBackground(Color.blue);

    }
    
    public void showWindow(){
        mainFrame.setVisible(true);
    }

}
