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
public class ActionPanel extends JPanel implements ActionListener{
    private final Mediator mediador;
    private final ListPanel actionListPanel;

    public ActionPanel(Mediator med){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        mediador=med;
        actionListPanel = new ListPanel(mediador);
        mediador.addActionList(actionListPanel);

        JButton executeButton = new JButton("Ejecutar");
        executeButton.setActionCommand("execute");
        executeButton.addActionListener(this);

        ControllerPanel controlerPanel = new ControllerPanel();
        controlerPanel.add(executeButton);

        add(actionListPanel);
        add(controlerPanel);
    }

    public void actionPerformed(ActionEvent e) {
     if (e.getActionCommand()=="execute" ){
            int index=actionListPanel.getSelectedItemIndex();
            mediador.executeActionWithIndex(index);
        }
    }


}
