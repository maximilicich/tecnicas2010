package mat7510.playground.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ClearButton extends JButton implements Command
{
   Mediator med;

   public ClearButton(ActionListener act, Mediator md)
   {
      super("Clear");
      addActionListener(act);
      med = md;
      med.registerClear(this);
   }
   public void Execute()
   {
      med.Clear();
   }
}
