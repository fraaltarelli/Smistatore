package it.objectmethod.smistatore;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Component;

import it.objectmethod.smistatore.model.UserHandlerReturnEntity;


@Component
public class SaxParser {


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
}
