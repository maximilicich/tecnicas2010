/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sergio
 */
public class StatePanel extends JPanel{
    private ListPanel stateListPanel;
    private Mediator mediador;

    public StatePanel(Mediator med){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        mediador = med;
   
        stateListPanel = new ListPanel(mediador);
        mediador.addStateList(stateListPanel);

        add(stateListPanel);
        add(new ControllerPanel());
    }

}
