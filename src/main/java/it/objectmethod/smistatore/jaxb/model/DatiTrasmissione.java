package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatiTrasmissione")

public class DatiTrasmissione {

	private IdTrasmittente idTrasmittente;
	private String progressivoInvio;
	private String formatoTrasmissione;
	private String codiceDestinatario;
	
	
	
	@XmlElement(name = "FormatoTrasmissione")
	public String getFormatoTrasmissione() {
		return formatoTrasmissione;
	}

	public void setFormatoTrasmissione(String formatoTrasmissione) {
		this.formatoTrasmissione = formatoTrasmissione;
	}

	@XmlElement(name = "CodiceDestinatario")
	public String getCodiceDestinatario() {
		return codiceDestinatario;
	}

	public void setCodiceDestinatario(String codiceDestinatario) {
		this.codiceDestinatario = codiceDestinatario;
	}

	@XmlElement(name = "ProgressivoInvio")
	public String getProgressivoInvio() {
		return progressivoInvio;
	}

	public void setProgressivoInvio(String progressivoInvio) {
		this.progressivoInvio = progressivoInvio;
	}
	

	@XmlElement(name = "IdTrasmittente")
	public IdTrasmittente getIdTrasmittente() {
		return idTrasmittente;
	}

	public void setIdTrasmittente(IdTrasmittente idTrasmittente) {
		this.idTrasmittente = idTrasmittente;
	}
	
	
}
