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


public class MoveButton extends JButton implements Command
{
  Mediator med;            //copy of the Mediator
public MoveButton(ActionListener fr, Mediator md)
 {
    super("Copy");         //create the button
    addActionListener(fr); //add its listener
    med = md;              //copy in the Mediator instance
    med.registerMove(this); //register with the Mediator   
 }
 public void Execute()
 {                         //execute the copy
    med.Move();
 }
}

