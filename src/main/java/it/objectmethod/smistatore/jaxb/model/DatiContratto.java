package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatiContratto")

public class DatiContratto {
	
	private Integer riferimentoNumeroLinea;
	private Integer idDocumento;
	private Integer numItem;
	private String codiceCUP;
	private String codiceCIG;
	
	
	@XmlElement(name= "RiferimentoNumeroLinea")
	public Integer getRiferimentoNumeroLinea() {
		return riferimentoNumeroLinea;
	}
	public void setRiferimentoNumeroLinea(Integer riferimentoNumeroLinea) {
		this.riferimentoNumeroLinea = riferimentoNumeroLinea;
	}
	
	@XmlElement(name= "IdDocumento")
	public Integer getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}
	
	@XmlElement(name= "NumItem")
	public Integer getNumItem() {
		return numItem;
	}
	public void setNumItem(Integer numItem) {
		this.numItem = numItem;
	}
	
	@XmlElement(name= "CodiceCUP")
	public String getCodiceCUP() {
		return codiceCUP;
	}
	public void setCodiceCUP(String codiceCUP) {
		this.codiceCUP = codiceCUP;
	}
	
	@XmlElement(name= "CodiceCIG")
	public String getCodiceCIG() {
		return codiceCIG;
	}
	public void setCodiceCIG(String codiceCIG) {
		this.codiceCIG = codiceCIG;
	}
	


}
