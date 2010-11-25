/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.smartBuilding.gui.view;

/**
 *
 * @author sergio
 */

import java.awt.Dimension;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

class DriverFileChooser {
	private String nomarch;
	private	 String dir;

	public DriverFileChooser(String dirDefecto){
		JFileChooser dialog = new JFileChooser(dirDefecto);
                dialog.setPreferredSize(new Dimension(600,450));
		Filtro filtro =new Filtro();
		dialog.setFileFilter(filtro);
		dialog.setMultiSelectionEnabled(false);
		int rVal = dialog.showOpenDialog(null);
		if(rVal == JFileChooser.APPROVE_OPTION) {
			nomarch=dialog.getSelectedFile().getName();
			dir=dialog.getCurrentDirectory().toString();
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