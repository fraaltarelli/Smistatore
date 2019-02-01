package it.objectmethod.smistatore;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.objectmethod.smistatore.model.UserHandlerReturnEntity;

@Component
class UserHandler extends DefaultHandler {

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
			System.out.println("Partita Iva: "+ buffer);
			entity.setPartitaIva(buffer.trim());
		}

		if (!bClienteTrovato && bCessionarioCommittente && qName.equalsIgnoreCase("CodiceFiscale")) {
			System.out.println("Codice fiscale: "+ buffer);
			entity.setCodiceFiscale(buffer.trim());
			bCessionarioCommittente = false;
		} 

		if(bDatiGeneraliDocumento && qName.equalsIgnoreCase("Data")) {
			System.out.println("Data documento: "+ buffer);
			entity.setDataDocumento(buffer.trim());
		}
		if(bDatiGeneraliDocumento && qName.equalsIgnoreCase("Numero")) {
			System.out.println("Numero documento: "+ buffer);
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