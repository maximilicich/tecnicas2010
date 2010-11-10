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

public class PickedKidsList extends JawtList
{
   Mediator med;
   public PickedKidsList(Mediator md)
   {
      super(20);
      med = md;
      med.registerPicked(this);
   }
}
