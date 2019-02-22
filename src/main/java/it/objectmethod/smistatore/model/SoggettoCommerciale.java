package it.objectmethod.smistatore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SoggettoCommerciale", uniqueConstraints= {
		@UniqueConstraint(columnNames="id_codice"),
        @UniqueConstraint(columnNames="codice_fiscale")
})
public class SoggettoCommerciale {
	
	@GeneratedValue
	@Id
	private Integer id;
	
	@Column(name="tipo")
	private String tipo;

	
	//DatiAnagrafici
	
	//IdFiscaleIva
	@Column(name="id_paese")
	private String idPaese;
	@Column(name="id_codice")
	private String idCodice;
	
	@Column(name="codice_fiscale")
	private String codiceFiscale;
	
	//Anagrafica
	@Column(name="denominazione")
	private String denominazione;
	
	@Column(name="regime_fiscale")
	private String regimeFiscale;
	
	
	//Sede
	@Column(name="indirizzo")
	private String indirizzo;
	@Column(name="cap")
	private String cap;
	@Column(name="comune")
	private String comune;
	@Column(name="provincia")
	private String provincia;
	@Column(name="nazione")
	private String nazione;
	
	
	@JsonIgnore
	@OneToMany
	List<Utente> utenti;

	@OneToMany
	List<Fattura> fatture;
	
	
	
	public List<Utente> getUtenti() {
		return utenti;
	}
	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}
	
	@JsonIgnore
	public List<Fattura> getFatture() {
		return fatture;
	}
	public void setFatture(List<Fattura> fatture) {
		this.fatture = fatture;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	public String getIdPaese() {
		return idPaese;
	}
	public void setIdPaese(String idPaese) {
		this.idPaese = idPaese;
	}
	public String getIdCodice() {
		return idCodice;
	}
	public void setIdCodice(String idCodice) {
		this.idCodice = idCodice;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getRegimeFiscale() {
		return regimeFiscale;
	}
	public void setRegimeFiscale(String regimeFiscale) {
		this.regimeFiscale = regimeFiscale;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
}
