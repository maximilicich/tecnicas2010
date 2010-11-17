/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

/**
 *
 * @author sergio
 */
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class JawtList extends JScrollPane{
   private JList listWindow;
   private JListData listContents;

   public JawtList(){
      listContents = new JListData();
      listWindow = new JList(listContents);
      listWindow.setPrototypeCellValue("Abcdefg Hijkmnop");
      getViewport().add(listWindow);

   }

   public void addListSelectionListener(ListSelectionListener iList){
      listWindow.addListSelectionListener(iList);
   }

   public void add(String s){
      listContents.addElement(s);
   }

   public void removeAt(int index){
      listContents.removeElement(index);
   }

   public void clear(){
      listContents.clear();
   }

   public void clearSelection(){
       listWindow.clearSelection();
   }

    public Object getSelectedValue(){
       return listWindow.getSelectedValue();
    }

    public int getSelectedIndex(){
        return listWindow.getSelectedIndex();
    }

}
//  =========================================
class JListData extends AbstractListModel{
   private Vector data;

   public JListData(){
      data = new Vector();
   }

   public int getSize(){
      return data.size();
   }

   public Object getElementAt(int index){
      return data.elementAt(index);
   }

   public void addElement(String s){
      data.addElement(s);
      fireIntervalAdded(this, data.size()-1, data.size());
   }

   public void removeElement(int index){
      data.removeElementAt(index);
      fireIntervalRemoved(this, 0, data.size());
   }

   public void clear(){
      int size= data.size();
      data = new Vector();
      fireIntervalRemoved(this, 0, size);
   }
}

