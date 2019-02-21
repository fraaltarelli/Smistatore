//package it.objectmethod.smistatore.model;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@Entity
//@Table(name="cliente", uniqueConstraints= {
//		@UniqueConstraint(columnNames="partita_iva"),
//        @UniqueConstraint(columnNames="codice_fiscale")
//})
//public class Cliente {
//	
//	@GeneratedValue
//	@Id
//	private Integer id;
//
//	@Column(name="name")   //denominazione
//	private String name;
//	
//	@Column(name="partita_iva")
//	private String partitaIva;
//	
//	@Column(name="codice_fiscale")
//	private String codiceFiscale;
//	
//	@JsonIgnore
//	@OneToMany
//	List<Utente> utenti;
//
//	@OneToMany(mappedBy= "cliente")
//	List<Fattura> fatture;
//	
//	@JsonIgnore
//	public List<Fattura> getFatture() {
//		return fatture;
//	}
//
//	public void setFatture(List<Fattura> fatture) {
//		this.fatture = fatture;
//	}
//
//	public List<Utente> getUtenti() {
//		return utenti;
//	}
//
//	public void setUtenti(List<Utente> utenti) {
//		this.utenti = utenti;
//	}
//
//	
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getPartitaIva() {
//		return partitaIva;
//	}
//
//	public void setPartitaIva(String partitaIva) {
//		this.partitaIva = partitaIva;
//	}
//
//	public String getCodiceFiscale() {
//		return codiceFiscale;
//	}
//
//	public void setCodiceFiscale(String codiceFiscale) {
//		this.codiceFiscale = codiceFiscale;
//	}
//
//}
