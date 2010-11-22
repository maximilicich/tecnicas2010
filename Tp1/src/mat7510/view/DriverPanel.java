/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sergio
 */
public class DriverPanel extends JPanel implements ActionListener,ListSelectionListener{

        ListPanel driversListPanel;
        Mediator mediador;
        
        public DriverPanel(Mediator med){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5,5,5,5));

            mediador=med;
            JButton updateButton = new JButton("Actualizar");
            updateButton.setActionCommand("update");
            updateButton.addActionListener(this);

            driversListPanel = new ListPanel(mediador);
            mediador.addDriverList(driversListPanel);
            driversListPanel.addListSelectionListener(this);

            ControllerPanel controllerPanel = new ControllerPanel();
            controllerPanel.add(updateButton);

            add(driversListPanel);
            add(controllerPanel);
    }

    private void selectFile(){
             DriverFileChooser open = new DriverFileChooser("./");
             String dir=open.getDirectorio();
             if(!dir.equals("")){
               mediador.addDriverWithName(dir);
              }

    }

    public void actionPerformed(ActionEvent e) {

         if (e.getActionCommand()=="update" )
            selectFile();
    }

    public void valueChanged(ListSelectionEvent e) {
     
        if(!e.getValueIsAdjusting()){
             JList theList = (JList)e.getSource();
             
             if (!theList.isSelectionEmpty())
                mediador.selectDriverWithIndex((String) theList.getSelectedValue());
        }
    }

}
