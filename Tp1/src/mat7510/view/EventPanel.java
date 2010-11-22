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
public class EventPanel extends JPanel{
    private final Mediator mediador;
    private final ListPanel eventListPanel;

    public EventPanel(Mediator med){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        mediador = med;

        eventListPanel = new ListPanel(mediador);
        mediador.addEventList(eventListPanel);

        add(eventListPanel);
        add(new ControllerPanel());
    }


}
