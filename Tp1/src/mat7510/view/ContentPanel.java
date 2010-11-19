/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author sergio
 */
public class ContentPanel extends JPanel{
    private Mediador mediador;
 
    public ContentPanel(Mediador med){
        mediador=med;        
        setLayout(new BorderLayout());

         JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 4));
        centerPanel.add(new DriverPanel(mediador));
        centerPanel.add(new StatePanel(mediador));
        centerPanel.add(new ActionPanel(mediador));
        centerPanel.add(new EventPanel(mediador));
        add(centerPanel, BorderLayout.CENTER);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4,5,25));
        topPanel.add(new JLabel("Drivers", JLabel.CENTER));
        topPanel.add(new JLabel("Estados", JLabel.CENTER));
        topPanel.add(new JLabel("Acciones", JLabel.CENTER));
        topPanel.add(new JLabel("Eventos", JLabel.CENTER));
        this.add(topPanel, BorderLayout.NORTH);

    }

}
