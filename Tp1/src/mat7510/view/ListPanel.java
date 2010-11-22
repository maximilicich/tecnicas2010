/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 *
 * @author sergio
 */
public class ListPanel extends JawtList implements ListSelectionListener{
    int tag;
    
    public ListPanel(){
            addListSelectionListener(this);
    }

    public void setTag(int tag){
        this.tag=tag;
    }

    public int getTag(){
        return tag;
    }

    public void valueChanged(ListSelectionEvent ls){
            JList obj = (JList)ls.getSource();
            if (obj.getSelectedIndex() >= 0){
              // informo al mediador
            }
    }

    public void addItem(Object obj){
        add(obj.toString());
    }

    public void addAll(Iterator it){
        while(it.hasNext()){
            Object obj = it.next();
            addItem(obj);
        }
    }
    
    public void removeItem(int Index){
        removeAt(Index);
    }
    
    public void refresh (){
    	this.clear();
    }

    private void fillDriversList(Vector vec){
            Iterator it = vec.iterator();
            while(it.hasNext()){
                Object data = it.next();
                add(data.toString());
            }
    }
}