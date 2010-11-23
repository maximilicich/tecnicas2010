/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.smartBuilding.gui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
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
public class ActionPanel extends JPanel implements ActionListener,ListSelectionListener{
    private final Mediator mediador;
    private final ListPanel actionListPanel;

    public ActionPanel(Mediator med){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        mediador=med;
        actionListPanel = new ListPanel();
        mediador.addActionList(actionListPanel);

        JButton executeButton = new JButton("Ejecutar");
        executeButton.setActionCommand("execute");
        executeButton.addActionListener(this);

        ControllerPanel controlerPanel = new ControllerPanel();
        controlerPanel.add(executeButton);

        actionListPanel.addListSelectionListener(this);

        add(actionListPanel);
        add(controlerPanel);
    }

    public void actionPerformed(ActionEvent e) {
     if (e.getActionCommand()=="execute" ){
            mediador.executeActionWithIndex((String)actionListPanel.getSelectedValue());
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
             JList theList = (JList)e.getSource();

             if (!theList.isSelectionEmpty())
                mediador.selectActionWithIndex((String) theList.getSelectedValue());
        }
    }


}
