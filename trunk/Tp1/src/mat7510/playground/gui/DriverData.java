package mat7510.playground.gui;

import java.util.*;
public class DriverData
{
   Vector drivers;
//------------------------------------------   
   public DriverData(String filename)
   {
      drivers = new Vector();
      InputFile f = new InputFile(filename);
      String s = f.readLine();
      while(s != null)
      {
         if(s.trim().length() > 0)
         {
         Driver k = new Driver(s);
         drivers.addElement(k);
         }
         s = f.readLine();
      }
   }
   //--------------------------------
   public Driver[] getData()
   {
      Driver[] kd = new Driver[drivers.size()];
      for(int i=0; i< drivers.size(); i++)
         kd[i] = (Driver)drivers.elementAt(i);
   return kd;
   }
   //--------------------------------
   public Enumeration elements()
   {
      return drivers.elements();
   }
   //-------------------------------

   public int size()
   {
      return drivers.size();
   }
   //--------------------------------
   public Driver getKid(int i)
   {
      return (Driver)drivers.elementAt(i);
   }
   //--------------------------------
   public Vector getKidData(int key)
   {
      Vector v = new Vector();
      for (int i = 0; i < drivers.size(); i++)
         v.addElement(getKid(i).getDriverName());
   return v;
   }
   //--------------------------------
   public int getTableKey(String tabName)
   {
      int key = -1;
      tabName = tabName.toLowerCase();
      if(tabName.equals("frname")) key = ParseVar.FRNAME;
      if(tabName.equals("lname")) key =  ParseVar.LNAME;
      if(tabName.equals("age")) key =    ParseVar.AGE;
      if(tabName.equals("club")) key =   ParseVar.CLUB;
      if(tabName.equals("time")) key =   ParseVar.TIME;

      return key;
   }
   //----------------------------
   public String getTableName(int i)
   {
     String name = ""; 
      switch(i)
      {
      case ParseVar.FRNAME:
           name = "frname";
         case ParseVar.LNAME:
            name = "lname";
         case ParseVar.AGE:
            name="age";
         case ParseVar.CLUB:
            name="club";
         case ParseVar.TIME:
            name="time";
      }
    return name;
   }
   //----------------------------
  
}

