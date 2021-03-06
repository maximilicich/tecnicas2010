/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.smartBuilding.gui.view;

import java.awt.*;
import javax.swing.*;

import mat7510.smartBuilding.gui.controller.Mediator;

/**
 *
 * @author Grupo 10
 */
@SuppressWarnings("serial")
public class ContentPanel extends JPanel{
    private Mediator mediador;
 
    public ContentPanel(Mediator med){
        mediador=med;        
        setLayout(new BorderLayout());

         JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 4));
        centerPanel.add(new DriverPanel(mediador));
        centerPanel.add(new StatePanel(mediador));
        centerPanel.add(new EventPanel(mediador));
        centerPanel.add(new ActionPanel(mediador));
        centerPanel.add(new RulePanel(mediador));
        add(centerPanel, BorderLayout.CENTER);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4,5,25));
        topPanel.add(new JLabel("Drivers", JLabel.CENTER));
        topPanel.add(new JLabel("Estados", JLabel.CENTER));
        topPanel.add(new JLabel("Eventos disponibles", JLabel.CENTER));
        topPanel.add(new JLabel("Acciones Disponibles", JLabel.CENTER));
        topPanel.add(new JLabel("Reglas", JLabel.CENTER));
        this.add(topPanel, BorderLayout.NORTH);

    }

}
