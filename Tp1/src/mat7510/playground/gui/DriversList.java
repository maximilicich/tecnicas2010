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

public class DriversList extends JawtList
implements ListSelectionListener
{
	DriverData kdata;
	Mediator med;

	public DriversList(Mediator md)
	{
		super(20);
		// kdata = new KidData ("c:\\trabajo\\patterns\\comportamiento\\PattComportamiento\\mediator\\50free.txt");
		kdata = new DriverData ("res/drivers_gui.txt");
		fillDriversList();
		med = md;
		med.registerKidList(this);
		addListSelectionListener(this);
	}

	//----------------------------------
	public void valueChanged(ListSelectionEvent ls)
	{
		JList obj = (JList)ls.getSource();
		if (obj.getSelectedIndex() >= 0)
			med.select();
	}
	//----------------------------------
	private void fillDriversList()
	{
		Enumeration ekid = kdata.elements();
		while (ekid.hasMoreElements())
		{
			Driver k =(Driver)ekid.nextElement();
			add(k.getDriverName());
		}
	}
}
