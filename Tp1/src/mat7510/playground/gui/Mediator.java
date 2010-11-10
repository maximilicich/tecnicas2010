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

public class Mediator
{
	private ClearButton clearButton;
	private MoveButton moveButton;
	private KTextField ktext;
	private DriversList klist;
	private PickedKidsList picked;

	public Mediator()
	{
	}
	//------------------------------------
	public void Move()
	{
		picked.add(ktext.getText());
		clearButton.setEnabled(true);
	}
	//------------------------------------
	public void init()
	{
		Clear();
	}
	//------------------------------------
	public void Clear()
	{
		ktext.setText("");
		moveButton.setEnabled(false);
		clearButton.setEnabled(false);
		picked.clear();
		klist.clearSelection();
		System.out.println("cleared");
	}
	//------------------------------------
	public void select()
	{
		String s = (String)klist.getSelectedValue();
		ktext.setText(s);
		moveButton.setEnabled(true);
		System.out.println("selected");
	}
	//------------------------------------
	public void registerClear(ClearButton cb)
	{
		clearButton = cb;
	}
	//------------------------------------
	public void registerMove(MoveButton mv)
	{
		moveButton = mv;
	}
	//------------------------------------
	public void registerText(KTextField tx)
	{
		ktext = tx;
	}
	//------------------------------------
	public void registerPicked(PickedKidsList pl)
	{
		picked = pl;
	}
	//------------------------------------
	public void registerKidList(DriversList kl)
	{
		klist = kl;
	}
}
