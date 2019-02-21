package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatiAnagraficiVettore")

public class DatiAnagraficiVettore {
	
	private IdFiscaleIva idFiscaleIva;
	private String codiceFiscale;
	private Anagrafica anagrafica;
	private String regimeFiscale;
	
	@XmlElement(name = "IdFiscaleIva")
	public IdFiscaleIva getIdFiscaleIva() {
		return idFiscaleIva;
	}
	public void setIdFiscaleIva(IdFiscaleIva idFiscaleIva) {
		this.idFiscaleIva = idFiscaleIva;
	}
	
	@XmlElement(name = "CodiceFiscale")
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	@XmlElement(name = "Anagrafica")
	public Anagrafica getAnagrafica() {
		return anagrafica;
	}
	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}
	
	@XmlElement(name = "RegimeFiscale")
	public String getRegimeFiscale() {
		return regimeFiscale;
	}
	public void setRegimeFiscale(String regimeFiscale) {
		this.regimeFiscale = regimeFiscale;
	}
	
	

}
