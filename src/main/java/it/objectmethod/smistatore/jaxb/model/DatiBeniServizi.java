package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatiBeniServizi")

public class DatiBeniServizi {
	
	private DettaglioLinee dettaglioLinee;
	private DatiRiepilogo datiRiepilogo;
	
	
	@XmlElement(name = "DettaglioLinee")
	public DettaglioLinee getDettaglioLinee() {
		return dettaglioLinee;
	}
	public void setDettaglioLinee(DettaglioLinee dettaglioLinee) {
		this.dettaglioLinee = dettaglioLinee;
	}
	
	
	@XmlElement(name = "DatiRiepilogo")
	public DatiRiepilogo getDatiRiepilogo() {
		return datiRiepilogo;
	}
	public void setDatiRiepilogo(DatiRiepilogo datiRiepilogo) {
		this.datiRiepilogo = datiRiepilogo;
	}

}
