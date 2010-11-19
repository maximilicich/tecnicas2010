/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author sergio
 */
public class ControllerPanel extends JPanel {

    public ControllerPanel(){
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setMaximumSize(new Dimension(400,20));
        setPreferredSize(new Dimension(400,45));
    }

}
