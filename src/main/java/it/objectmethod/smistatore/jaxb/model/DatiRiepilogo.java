package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatiRiepilogo")

public class DatiRiepilogo {
	
	private Double aliquotaIVA;
	private Double imponibileImporto;
	private Double imposta;
	private String esigibilitaIVA;
	
	
	@XmlElement(name= "AliquotaIVA")
	public Double getAliquotaIVA() {
		return aliquotaIVA;
	}
	public void setAliquotaIVA(Double aliquotaIVA) {
		this.aliquotaIVA = aliquotaIVA;
	}
	
	@XmlElement(name= "ImponibileImporto")
	public Double getImponibileImporto() {
		return imponibileImporto;
	}
	public void setImponibileImporto(Double imponibileImporto) {
		this.imponibileImporto = imponibileImporto;
	}
	
	@XmlElement(name= "Imposta")
	public Double getImposta() {
		return imposta;
	}
	public void setImposta(Double imposta) {
		this.imposta = imposta;
	}
	
	@XmlElement(name= "EsigibilitaIVA")
	public String getEsigibilitaIVA() {
		return esigibilitaIVA;
	}
	public void setEsigibilitaIVA(String esigibilitaIVA) {
		this.esigibilitaIVA = esigibilitaIVA;
	}
	

	

}
