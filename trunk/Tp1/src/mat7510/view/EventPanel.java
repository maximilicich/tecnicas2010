/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sergio
 */
public class EventPanel extends JPanel implements ActionListener {
    private final Mediator mediador;
    private final ListPanel eventListPanel;

    public EventPanel(Mediator med){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        mediador = med;
        JButton deleteButton = new JButton("Eliminar");
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(this);

        JButton addButton = new JButton("Agregar");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);

        JButton detailButton = new JButton("Descripcion");
        detailButton.setActionCommand("description");
        detailButton.addActionListener(this);

        ControllerPanel controllerEventPanel = new ControllerPanel();
        controllerEventPanel.add(addButton);
        controllerEventPanel.add(deleteButton);
        controllerEventPanel.add(detailButton);

       eventListPanel = new ListPanel(mediador);
       mediador.addEventList(eventListPanel);

        add(eventListPanel);
        add(controllerEventPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand()=="delete" ){
            int index = eventListPanel.getSelectedIndex();

            if(index>=0)
                eventListPanel.removeItem(index);
            mediador.removeEventWithIndex(index);

        }else if (e.getActionCommand()=="add" ){
            mediador.addEvent();
        }else if (e.getActionCommand()=="description" ){

        }
    }

}
