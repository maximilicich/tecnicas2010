package mat7510.smartBuilding.model;
import java.io.File;
import java.net.URL;

public class WorkingDirectory { 
	
	private static File workingDirectory; 
	
	
	public static File get() {      
		
		if(workingDirectory == null) {    
			try {               
				URL url = WorkingDirectory.class.getResource("WorkingDirectory.class");  
				 
				if(url.getProtocol().equals("file")) {    
					File file = new File(url.toURI());       
					file = file.getParentFile().getParentFile().getParentFile().getParentFile();  
					
					workingDirectory = file;    
					
					} 
				else 
					if(url.getProtocol().equals("jar")){                  
						String expected = "!/util/WorkingDirectory.class";  
						String s = url.toString();    
						s = s.substring(4);          
						s = s.substring(0, s.length() - expected.length());     
						File file = new File(new URL(s).toURI());    
						file = file.getParentFile();                  
						workingDirectory = file;     
						}           
			} catch(Exception e) {    
					workingDirectory = new File(".");          
			}    
		}
		return workingDirectory;
	}       
 
	
	
	public static void main(String[] args) {   
		File file = WorkingDirectory.get();
		String path = file.getAbsolutePath();
		String pathjar = "\\res\\deviceDriverConfig.xml";
		path = path + pathjar;
		System.out.println (path);
		//System.out.println(WorkingDirectory.get());   
		System.out.println(System.getProperty("user.dir"));   
	}
	
}