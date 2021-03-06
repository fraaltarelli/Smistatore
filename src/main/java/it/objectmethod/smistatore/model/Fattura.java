package it.objectmethod.smistatore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="fattura")
public class Fattura {

	public enum Stato {
	    PROCESSED,
	    DISCARDED,
	    CHECK_REQ,
	    REFUSED,
	    SENT
	}

	
	@GeneratedValue
	@Id
	private Integer id;
	
	@Column(name="nome_file")
	private String nomeFile;
	
	@ManyToOne
	@JoinColumn(name = "id_mittente")
	private SoggettoCommerciale cp;
	
	@ManyToOne
	@JoinColumn(name = "id_destinatario")
	private SoggettoCommerciale cc;

	@Column(name="numero_documento")
	private Integer numeroDocumento;
	
	@Column(name="data_documento")
	private String dataDocumento;
	
	@Enumerated(EnumType.STRING)
	private Stato stato;
	
	
	
	

	

	public SoggettoCommerciale getCp() {
		return cp;
	}

	public void setCp(SoggettoCommerciale cp) {
		this.cp = cp;
	}

	public SoggettoCommerciale getCc() {
		return cc;
	}

	public void setCc(SoggettoCommerciale cc) {
		this.cc = cc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(String dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
	

}
