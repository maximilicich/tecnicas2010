package mat7510.playground.gui;

import java.util.*;

public class kidIter 
   implements Enumeration
{
   String clubMask;
   int index;
   Driver kid;
   Enumeration ke;
   DriverData kdata;
//----------------------------------------
   public kidIter(DriverData kd, String club)
   {
      clubMask = club;
      kdata = kd;
      index = 0;
      kid = null;
      ke = kdata.elements();
   }
//----------------------------------------
   public boolean hasMoreElements()
   {
      boolean found = false;
      while(ke.hasMoreElements() && ! found)
      {
      kid = (Driver)ke.nextElement();
      found = kid.getDriverName().equals(clubMask);
      }
      if(!found) kid = null;
      return found;
   }
//----------------------------------------
   public Object nextElement()
   {
      if(kid != null)
         return kid;
      else
         throw new NoSuchElementException();
   }
}
