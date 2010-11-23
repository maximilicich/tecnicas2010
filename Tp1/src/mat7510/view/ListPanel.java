/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.view;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author sergio
 */
@SuppressWarnings("serial")
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

    @SuppressWarnings("unused")
	private void fillDriversList(Vector<Object> vec){
            Iterator<Object> it = vec.iterator();
            while(it.hasNext()){
                Object data = it.next();
                add(data.toString());
            }
    }
}