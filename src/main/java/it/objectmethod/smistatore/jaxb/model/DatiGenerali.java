package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatiGenerali")

public class DatiGenerali {

	private DatiGeneraliDocumento datiGeneraliDocumento;
	private DatiOrdineAcquisto datiOrdineAcquisto;
	private DatiContratto datiContratto;
	private DatiConvenzione datiConvenzione;
	private DatiRicezione datiRicezione;
	private DatiTrasporto datiTrasporto;
	
	@XmlElement(name = "DatiGeneraliDocumento")
	public DatiGeneraliDocumento getDatiGeneraliDocumento() {
		return datiGeneraliDocumento;
	}
	public void setDatiGeneraliDocumento(DatiGeneraliDocumento datiGeneraliDocumento) {
		this.datiGeneraliDocumento = datiGeneraliDocumento;
	}
	
	@XmlElement(name = "DatiOrdineAcquisto")
	public DatiOrdineAcquisto getDatiOrdineAcquisto() {
		return datiOrdineAcquisto;
	}
	public void setDatiOrdineAcquisto(DatiOrdineAcquisto datiOrdineAcquisto) {
		this.datiOrdineAcquisto = datiOrdineAcquisto;
	}
	
	@XmlElement(name = "DatiContratto")
	public DatiContratto getDatiContratto() {
		return datiContratto;
	}
	public void setDatiContratto(DatiContratto datiContratto) {
		this.datiContratto = datiContratto;
	}
	
	@XmlElement(name = "DatiConvenzione")
	public DatiConvenzione getDatiConvenzione() {
		return datiConvenzione;
	}
	public void setDatiConvenzione(DatiConvenzione datiConvenzione) {
		this.datiConvenzione = datiConvenzione;
	}
	
	@XmlElement(name = "DatiRicezione")
	public DatiRicezione getDatiRicezione() {
		return datiRicezione;
	}
	public void setDatiRicezione(DatiRicezione datiRicezione) {
		this.datiRicezione = datiRicezione;
	}
	
	@XmlElement(name = "DatiTrasporto")
	public DatiTrasporto getDatiTrasporto() {
		return datiTrasporto;
	}
	public void setDatiTrasporto(DatiTrasporto datiTrasporto) {
		this.datiTrasporto = datiTrasporto;
	}




}
