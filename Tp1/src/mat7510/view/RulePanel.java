/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sergio
 */
public class RulePanel extends JPanel implements ActionListener {
    private Mediator mediator;
    private ListPanel ruleListPanel;

    public RulePanel(Mediator med){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));
        mediator = med;
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

       ruleListPanel = new ListPanel();
       mediator.addRuleList(ruleListPanel);

        add(ruleListPanel);
        add(controllerEventPanel);
    }

    public void actionPerformed(ActionEvent e) {
        int index = ruleListPanel.getSelectedIndex();

        if (e.getActionCommand()=="delete" ){
            
            if(index>=0)
                ruleListPanel.removeItem(index);
                mediator.removeRuleWithIndex((String) ruleListPanel.getSelectedValue());

        }else if (e.getActionCommand()=="add" ){
            mediator.createNewRule();
        }else if (e.getActionCommand()=="description" ){
            /*
                if(index <0){
                    JOptionPane.showMessageDialog(this ,"Debe seleccionar una regla","Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
*/
                mediator.showDescriptionRule((String) ruleListPanel.getSelectedValue());

        }
    }

}
