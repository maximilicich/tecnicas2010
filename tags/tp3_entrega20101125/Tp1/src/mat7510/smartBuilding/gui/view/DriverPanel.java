/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.smartBuilding.gui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mat7510.smartBuilding.gui.controller.Mediator;

/**
 *
 * @author sergio
 */
@SuppressWarnings("serial")
public class DriverPanel extends JPanel implements ActionListener,ListSelectionListener{

	ListPanel driversListPanel;
	Mediator mediator;

	public DriverPanel(Mediator med){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(5,5,5,5));

		mediator=med;
		JButton addButton = new JButton("Agregar");
		addButton.setActionCommand("add");
		addButton.addActionListener(this);

                JButton refreshButton = new JButton("Recargar");
		refreshButton.setActionCommand("reload");
		refreshButton.addActionListener(this);

		driversListPanel = new ListPanel();
		mediator.addDriverList(driversListPanel);
		driversListPanel.addListSelectionListener(this);

		ControllerPanel controllerPanel = new ControllerPanel();
		controllerPanel.add(addButton);
                controllerPanel.add(refreshButton);

		add(driversListPanel);
		add(controllerPanel);
	}

	public void valueChanged(ListSelectionEvent e) {

		if(!e.getValueIsAdjusting()){
			JList theList = (JList)e.getSource();

			if (!theList.isSelectionEmpty())
				mediator.selectDriverWithIndex((String) theList.getSelectedValue());
		}
	}

	private void selectFile(){
		DriverFileChooser open = new DriverFileChooser("./");
		String dir=open.getDirectorio();
		if(!dir.equals("")){
			mediator.addDriverWithName(dir);
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("add") )
			selectFile();
                else if(e.getActionCommand().equals("reload"))
                    mediator.reload();
	}


}
