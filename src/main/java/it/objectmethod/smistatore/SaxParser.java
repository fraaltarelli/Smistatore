package it.objectmethod.smistatore;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Component;

import it.objectmethod.smistatore.model.UserHandlerReturnEntity;


@Component
public class SaxParser {
	
	byte[] pdf;
	private String nomeFile;

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}


	public UserHandlerReturnEntity leggiXml(String pathXml) {
		UserHandler userHandler= null;

		try {
			File inputFile = new File(pathXml);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			userHandler = new UserHandler();
			saxParser.parse(inputFile, userHandler);  

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userHandler.entity;
	}
	
	public UserHandlerReturnEntity verificaXml(InputStream is) {
		UserHandlerVerificaXml userHandler= null;

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			userHandler = new UserHandlerVerificaXml();
			saxParser.parse(is, userHandler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userHandler.entity;
	}


}



