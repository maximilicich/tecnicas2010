/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author sergio
 */
public class AddEventDialog extends JDialog {
    Mediador mediador;
    JComboBox typeEvent;
    JTextField nameEvent;

    public AddEventDialog(Mediador med){
        mediador=med;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel typePanel = new  JPanel();
        typePanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));

        typePanel.add(new JLabel("Tipo:"));


        
    }

}
