package it.objectmethod.smistatore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="cliente", uniqueConstraints= {
		@UniqueConstraint(columnNames="partita_iva"),
        @UniqueConstraint(columnNames="codice_fiscale")
})
public class Cliente {
	
	@GeneratedValue
	@Id
	private Integer id;

	@Column(name="name")
	private String name;
	
	@Column(name="partita_iva")
	private String partitaIva;
	
	@Column(name="codice_fiscale")
	private String codiceFiscale;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

}
