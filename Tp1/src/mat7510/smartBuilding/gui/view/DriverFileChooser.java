/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.smartBuilding.gui.view;

/**
 *
 * @author sergio
 */

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

class DriverFileChooser {
	private String nomarch;
	private	 String dir;

	public DriverFileChooser(String dirDefecto){
		JFileChooser c = new JFileChooser(dirDefecto);
		Filtro pp =new Filtro();
		c.setFileFilter(pp);
		c.setMultiSelectionEnabled(false);
		int rVal = c.showOpenDialog(null);
		if(rVal == JFileChooser.APPROVE_OPTION) {
			nomarch=c.getSelectedFile().getName();
			dir=c.getCurrentDirectory().toString();
			if (nomarch.indexOf(".xml") == -1){
				nomarch="";
				dir="";
				new Mensaje(nomarch);
			   }
		  }

		 if(rVal == JFileChooser.CANCEL_OPTION) {
			 nomarch="";
			 dir="";
			}
	}

	class Filtro extends FileFilter{
		public boolean accept(File e) {
	  	    nomarch=e.getName();
	        if (nomarch.indexOf(".xml")!= -1)
				{return true;}
			return false;
		  }
		public String getDescription() {
			return "*.xml";
		  }
	  }

	@SuppressWarnings("serial")
	class Mensaje extends JFrame {
	    public Mensaje(String tex)  {
		   JOptionPane.showMessageDialog(this,
		   "El archivo "+tex+" no tiene un formato valido",
		   "",
		   JOptionPane.OK_OPTION );
		 }
	  }

	public String getDirectorio(){
		return dir.equals("") ? "" : (dir+"/"+nomarch);
	}

	public String getNomArch(){
		return dir.equals("") ? "" : (nomarch);
	}
}