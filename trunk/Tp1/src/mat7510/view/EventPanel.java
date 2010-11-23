/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sergio
 */
@SuppressWarnings("serial")
public class EventPanel extends JPanel implements ListSelectionListener{
    private final Mediator mediador;
    private final ListPanel eventListPanel;

    public EventPanel(Mediator med){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        mediador = med;

        eventListPanel = new ListPanel();
        eventListPanel.addListSelectionListener(this);
        mediador.addEventList(eventListPanel);

        add(eventListPanel);
        add(new ControllerPanel());
    }

    public void valueChanged(ListSelectionEvent e) {
        eventListPanel.clearSelection();
    }


}
