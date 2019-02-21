package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatiTrasporto")

public class DatiTrasporto {

	private DatiAnagraficiVettore datiAnagraficiVettore;
	private String dataOraConsegna;
	
	
	@XmlElement(name = "DatiAnagraficiVettore")
	public DatiAnagraficiVettore getDatiAnagraficiVettore() {
		return datiAnagraficiVettore;
	}
	public void setDatiAnagraficiVettore(DatiAnagraficiVettore datiAnagraficiVettore) {
		this.datiAnagraficiVettore = datiAnagraficiVettore;
	}
	
	@XmlElement(name = "DataOraConsegna")
	public String getDataOraConsegna() {
		return dataOraConsegna;
	}
	public void setDataOraConsegna(String dataOraConsegna) {
		this.dataOraConsegna = dataOraConsegna;
	}
	
	
	
}
