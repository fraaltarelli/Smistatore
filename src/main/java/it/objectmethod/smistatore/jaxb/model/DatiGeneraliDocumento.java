package it.objectmethod.smistatore.jaxb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatiGeneraliDocumento")

public class DatiGeneraliDocumento {
	
	private String tipoDocumento;
	private String divisa;
	private String data;
	private Integer numero;
	private List<String> causale;
	
	
	@XmlElement(name = "TipoDocumento")
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	@XmlElement(name = "Divisa")
	public String getDivisa() {
		return divisa;
	}
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}
	
	@XmlElement(name = "Data")
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@XmlElement(name = "Numero")
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	@XmlElement(name = "Causale")
	public List<String> getCausale() {
		return causale;
	}
	public void setCausale(List<String> causale) {
		this.causale = causale;
	}


}
