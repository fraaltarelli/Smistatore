package it.objectmethod.smistatore;

import org.springframework.stereotype.Component;


@Component
public class PathUtil {
	
	public String ritornaPath(String pathBody, String subFolder) {
		return pathBody+"\\"+subFolder;
	}
	
	public String ritornaPath(String pathBody, String subFolder, String fileName) {
		return pathBody+"\\"+subFolder+"\\"+fileName;
	}
	

}
