package it.objectmethod.smistatore.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DettaglioPagamento")

public class DettaglioPagamento {
	
	private String modalitaPagamento;
	private String dataScadenzaPagamento;
	private Double importoPagamento;
	
	@XmlElement(name = "ModalitaPagamento")
	public String getModalitaPagamento() {
		return modalitaPagamento;
	}
	public void setModalitaPagamento(String modalitaPagamento) {
		this.modalitaPagamento = modalitaPagamento;
	}
	
	@XmlElement(name = "DataScadenzaPagamento")
	public String getDataScadenzaPagamento() {
		return dataScadenzaPagamento;
	}
	public void setDataScadenzaPagamento(String dataScadenzaPagamento) {
		this.dataScadenzaPagamento = dataScadenzaPagamento;
	}
	
	@XmlElement(name = "ImportoPagamento")
	public Double getImportoPagamento() {
		return importoPagamento;
	}
	public void setImportoPagamento(Double importoPagamento) {
		this.importoPagamento = importoPagamento;
	}
	


}
