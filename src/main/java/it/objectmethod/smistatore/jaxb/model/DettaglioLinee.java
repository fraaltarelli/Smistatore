package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DettaglioLinee")

public class DettaglioLinee {
	
	private Integer numeroLinea;
	private String descrizione;
	private Double quantita;
	private Double prezzoUnitario;
	private Double prezzoTotale;
	private Double aliquotaIVA;
	
	
	@XmlElement(name = "NumeroLinea")
	public Integer getNumeroLinea() {
		return numeroLinea;
	}
	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}
	
	@XmlElement(name = "Descrizione")
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@XmlElement(name = "Quantita")
	public Double getQuantita() {
		return quantita;
	}
	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}
	
	@XmlElement(name = "PrezzoUnitario")
	public Double getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public void setPrezzoUnitario(Double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	
	@XmlElement(name = "PrezzoTotale")
	public Double getPrezzoTotale() {
		return prezzoTotale;
	}
	public void setPrezzoTotale(Double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	
	@XmlElement(name = "AliquotaIVA")
	public Double getAliquotaIVA() {
		return aliquotaIVA;
	}
	public void setAliquotaIVA(Double aliquotaIVA) {
		this.aliquotaIVA = aliquotaIVA;
	}
	
	
	

}
