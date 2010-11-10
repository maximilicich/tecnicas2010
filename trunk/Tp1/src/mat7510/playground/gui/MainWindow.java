package mat7510.playground.gui;


import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * SACADO DEL EJEMPLO DE MEDIATOR DE PANTALEO
 * 
 * @author MA_Xx
 *
 */
public class MainWindow extends JxFrame implements ActionListener
{
	DriversList kidList;
	DriversList kidList2;
	DriversList kidList3;
	DriversList kidList4;
	PickedKidsList picked;
	KTextField tx;
	MoveButton Move;
	ClearButton Clear;
	Mediator med;

	public MainWindow()
	{
		super("Smart Building Config Manager");

		// se crea el mediator
		Mediator med = new Mediator();

		JPanel jp = new JPanel();
		getContentPane().add(jp);
		jp.setLayout(new BorderLayout());
		
		JPanel center = new JPanel();
		
		JPanel outerLeft = new JPanel();
		JPanel innerLeft = new JPanel();
		JPanel outerRight = new JPanel();
		JPanel innerRight = new JPanel();
		
		jp.add("Center", center);
		center.setLayout(new GridLayout(1, 4));
		// center.add(new Label("Tables"));
		center.add(outerLeft);
		center.add(innerLeft);      
		center.add(innerRight);
		center.add(outerRight);
		
		outerLeft.setBorder(new EmptyBorder(5,5,5,5));
		innerLeft.setBorder(new EmptyBorder(5,5,5,5));
		innerRight.setBorder(new EmptyBorder(5,5,5,5));
		outerRight.setBorder(new EmptyBorder(5,5,5,5));

		// se crean los controles
		kidList = new DriversList( med);
		outerLeft.setLayout(new BorderLayout());
		outerLeft.add("Center", kidList);

		// se crean los controles
		kidList2 = new DriversList( med);
		innerLeft.setLayout(new BorderLayout());
		innerLeft.add("Center", kidList2);


		outerRight.setLayout(new BorderLayout());
		tx = new KTextField(med);

		Move = new MoveButton(this, med);
		Clear = new ClearButton(this, med);
		JPanel rtop = new JPanel();
		jp.add ("North", rtop);
		//rtop.add(tx);
		//rtop.add(Move);
		//rtop.add(Clear);
		rtop.setLayout(new GridLayout(1, 4));
		JPanel titles = new JPanel();
		titles.setLayout(new GridLayout(1, 4));
		titles.add(new Label("Drivers"));
		titles.add(new Label("Estados"));
		titles.add(new Label("Acciones"));
		titles.add(new Label("Eventos"));
		rtop.add(titles);
		
		picked = new PickedKidsList(med);
//		innerRight.add("Center", picked);

		kidList3 = new DriversList( med);
		innerRight.setLayout(new BorderLayout());
		innerRight.add("Center", kidList3);
		
		
		kidList4 = new DriversList( med);
		outerRight.setLayout(new BorderLayout());
		outerRight.add("Center", kidList4);

		
		med.init();
		setSize(new Dimension(400,300));
		setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
		setVisible(true);
	}
	//---------------------------------------
	public void actionPerformed(ActionEvent e)
	{
		Command comd = (Command)e.getSource();
		comd.Execute();
	}
	//---------------------------------------
	static public void main(String argv[])
	{
		new MainWindow();
	}
}
