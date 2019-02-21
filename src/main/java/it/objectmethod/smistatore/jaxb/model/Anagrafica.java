package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Anagrafica")
public class Anagrafica {
	
	private String denominazione;

	@XmlElement(name = "Denominazione")
	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	

}
