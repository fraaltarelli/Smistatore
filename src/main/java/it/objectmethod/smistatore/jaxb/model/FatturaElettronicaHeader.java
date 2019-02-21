package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FatturaElettronicaHeader")
//@XmlType(propOrder = { "datiTrasmissione", "cedentePrestatore", "cessionarioCommittente"})
public class FatturaElettronicaHeader {
	
	private DatiTrasmissione datiTrasmissione;
	private CedentePrestatore cedentePrestatore;
	private CessionarioCommittente cessionarioCommittente;
	

	@XmlElement(name = "CessionarioCommittente")
	public CessionarioCommittente getCessionarioCommittente() {
		return cessionarioCommittente;
	}

	public void setCessionarioCommittente(CessionarioCommittente cessionarioCommittente) {
		this.cessionarioCommittente = cessionarioCommittente;
	}

	
	@XmlElement(name = "CedentePrestatore")
	public CedentePrestatore getCedentePrestatore() {
		return cedentePrestatore;
	}

	public void setCedentePrestatore(CedentePrestatore cedentePrestatore) {
		this.cedentePrestatore = cedentePrestatore;
	}
	

	@XmlElement(name = "DatiTrasmissione")
	public DatiTrasmissione getDatiTrasmissione() {
		return datiTrasmissione;
	}

	public void setDatiTrasmissione(DatiTrasmissione datiTrasmissione) {
		this.datiTrasmissione = datiTrasmissione;
	}

}
