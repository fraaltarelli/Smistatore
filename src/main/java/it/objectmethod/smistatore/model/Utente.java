package it.objectmethod.smistatore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="utente")
public class Utente {
	
	@GeneratedValue
	@Id
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="fattura_id")
	private Integer fatturaId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getFatturaId() {
		return fatturaId;
	}

	public void setFatturaId(Integer fatturaId) {
		this.fatturaId = fatturaId;
	}

}
