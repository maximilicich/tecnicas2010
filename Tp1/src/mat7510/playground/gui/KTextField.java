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

public class KTextField extends JTextField
{
 Mediator med;
 public KTextField(Mediator md)
 {
    super(10);
    med = md;
    med.registerText(this);
 }
}
