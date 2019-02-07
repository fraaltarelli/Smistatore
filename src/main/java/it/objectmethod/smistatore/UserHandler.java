package it.objectmethod.smistatore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.objectmethod.smistatore.model.UserHandlerReturnEntity;

@Component
class UserHandler extends DefaultHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserHandler.class);

	UserHandlerReturnEntity entity = new UserHandlerReturnEntity();
	boolean bCessionarioCommittente = false;
	boolean bDatiGeneraliDocumento = false;
	boolean bClienteTrovato = false;

	String tempValueAttachment = "";
	String buffer = "";

	@Override
	public void startElement(String uri, 
			String localName, String qName, Attributes attributes)
					throws SAXException {

		if (qName.equalsIgnoreCase("CessionarioCommittente")) {
			bCessionarioCommittente = true;

		} 
		if (qName.equalsIgnoreCase("DatiGeneraliDocumento")) {
			bDatiGeneraliDocumento = true;;
		}
	}


	@Override
	public void endElement(
			String uri, String localName, String qName) throws SAXException {


		if (bCessionarioCommittente && qName.equalsIgnoreCase("IdCodice")) {
			LOGGER.debug("Partita Iva: "+ buffer);
//			System.out.println("Partita Iva: "+ buffer);
			entity.setPartitaIva(buffer.trim());
		}

		if (!bClienteTrovato && bCessionarioCommittente && qName.equalsIgnoreCase("CodiceFiscale")) {
			LOGGER.debug("Codice fiscale: "+ buffer);
			entity.setCodiceFiscale(buffer.trim());
			bCessionarioCommittente = false;
		} 

		if(bDatiGeneraliDocumento && qName.equalsIgnoreCase("Data")) {
			LOGGER.debug("Data documento: "+ buffer);
			entity.setDataDocumento(buffer.trim());
		}
		if(bDatiGeneraliDocumento && qName.equalsIgnoreCase("Numero")) {
			LOGGER.debug("Numero documento: "+ buffer);
			entity.setNumeroDocumento(Integer.parseInt(buffer.trim()));
			bDatiGeneraliDocumento = false;
		}

		buffer="";
	}


	@Override
	public void characters(
			char ch[], int start, int length) throws SAXException {

		buffer += new String(ch,start, length);

	} 

}