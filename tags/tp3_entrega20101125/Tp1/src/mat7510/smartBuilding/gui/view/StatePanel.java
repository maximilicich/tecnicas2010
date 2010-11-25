/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.smartBuilding.gui.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mat7510.smartBuilding.gui.controller.Mediator;

/**
 *
 * @author sergio
 */
@SuppressWarnings("serial")
public class StatePanel extends JPanel implements ListSelectionListener{
    private ListPanel stateListPanel;
    private Mediator mediador;

    public StatePanel(Mediator med){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        mediador = med;
   
        stateListPanel = new ListPanel();
        mediador.addStateList(stateListPanel);
        stateListPanel.addListSelectionListener(this);
        add(stateListPanel);
        add(new ControllerPanel());
    }

    public void valueChanged(ListSelectionEvent e) {
        stateListPanel.clearSelection();

    }

}
