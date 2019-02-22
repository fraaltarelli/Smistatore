package it.objectmethod.smistatore.jaxb;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.objectmethod.smistatore.SmistatoreScheduler;
import it.objectmethod.smistatore.jaxb.model.FatturaElettronica;

@Component
public class FatturaElettronicaMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmistatoreScheduler.class);

	public FatturaElettronica unmarshalFattura(String pathXml) {

		FatturaElettronica fattura = null;

		try{
			File file = new File(pathXml);
			JAXBContext jContext = JAXBContext.newInstance(FatturaElettronica.class);
			Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
			fattura=(FatturaElettronica) unmarshallerObj.unmarshal(new FileReader(file));

		}catch(Exception e){
			LOGGER.debug("Errore unmarshalling fattura");
			e.printStackTrace();
		}

		return fattura;
	}


	public FatturaElettronica unmarshalFattura(InputStream is) {

		FatturaElettronica fattura = null;

		try{
			JAXBContext jContext = JAXBContext.newInstance(FatturaElettronica.class);
			Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
			fattura=(FatturaElettronica) unmarshallerObj.unmarshal(is);

		}catch(Exception e){
			LOGGER.debug("Errore unmarshalling fattura");
			e.printStackTrace();
		}

		return fattura;
	}
}
