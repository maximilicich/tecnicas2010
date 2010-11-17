package mat7510.playground.gui;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class DriversList extends JawtList
implements ListSelectionListener{
    DriverData kdata;
    Mediator med;

    public DriversList(Mediator md){
            super(1);
            // kdata = new KidData ("c:\\trabajo\\patterns\\comportamiento\\PattComportamiento\\mediator\\50free.txt");
            kdata = new DriverData ("/home/sergio/Escritorio/Facu/Tecnicas/Tp/tecnicas2010/Tp1/src/res/drivers_gui.txt");
            fillDriversList();
            med = md;
            med.registerKidList(this);
            addListSelectionListener(this);
    }


    public void valueChanged(ListSelectionEvent ls){
            JList obj = (JList)ls.getSource();
            if (obj.getSelectedIndex() >= 0)
                    med.select();
    }

    private void fillDriversList(){
            Enumeration ekid = kdata.elements();
            while (ekid.hasMoreElements()){
                    Driver k =(Driver)ekid.nextElement();
                    add(k.getDriverName());
            }
    }
}
