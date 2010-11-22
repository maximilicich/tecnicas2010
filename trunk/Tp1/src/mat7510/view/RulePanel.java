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
public class RulePanel extends JPanel implements ActionListener {
    private Mediator mediador;
    private ListPanel ruleListPanel;

    public RulePanel(Mediator med){
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

       ruleListPanel = new ListPanel(mediador);
       mediador.addRuleList(ruleListPanel);

        add(ruleListPanel);
        add(controllerEventPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand()=="delete" ){
            int index = ruleListPanel.getSelectedIndex();

            if(index>=0)
                ruleListPanel.removeItem(index);
                mediador.removeEventWithIndex((String) ruleListPanel.getSelectedValue());

        }else if (e.getActionCommand()=="add" ){
            mediador.addEvent();
        }else if (e.getActionCommand()=="description" ){

        }
    }

}
