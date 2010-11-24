/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.smartBuilding.gui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mat7510.smartBuilding.gui.controller.Mediator;

/**
 *
 * @author sergio
 */
@SuppressWarnings("serial")
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

        JButton detailButton = new JButton("Descripción");
        detailButton.setActionCommand("description");
        detailButton.addActionListener(this);

        JButton editButton = new JButton("Editar");
        editButton.setActionCommand("edit");
        editButton.addActionListener(this);

        JPanel top = new JPanel();
        top.add(addButton);
        top.add(deleteButton);

        JPanel botton = new JPanel();
        botton.add(detailButton);
        botton.add(editButton);

        ControllerPanel controllerEventPanel = new ControllerPanel();
        controllerEventPanel.add(top);
        controllerEventPanel.add(botton);
   //     controllerEventPanel.add(detailButton);
  //      controllerEventPanel.add(editButton);

       ruleListPanel = new ListPanel();
       mediator.addRuleList(ruleListPanel);

        add(ruleListPanel);
        add(controllerEventPanel);
    }

    public void actionPerformed(ActionEvent e) {
        int index = ruleListPanel.getSelectedIndex();

         if (e.getActionCommand().equals("add") ){
            mediator.createNewRule();
        }else{

              if(index <0){
                  JOptionPane.showMessageDialog(mediator.getWindows() ,"Debe seleccionar una regla","Warning", JOptionPane.WARNING_MESSAGE);
                  return;
              }

              if (e.getActionCommand().equals("delete") ){
                  mediator.removeRuleWithIndex((String) ruleListPanel.getSelectedValue());
                  ruleListPanel.removeItem(index);
              }else if (e.getActionCommand().equals("description") ){
                      mediator.showDescriptionRule((String) ruleListPanel.getSelectedValue());

              }else  if (e.getActionCommand().equals("edit") ){
                       mediator.showEditRule((String) ruleListPanel.getSelectedValue());
                    }
        }
    }

}
