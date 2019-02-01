package it.objectmethod.smistatore.controller.rest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.smistatore.model.Fattura;
import it.objectmethod.smistatore.model.Utente;
import it.objectmethod.smistatore.repository.ApplicationConfigRepository;
import it.objectmethod.smistatore.repository.FatturaRepository;
import it.objectmethod.smistatore.repository.UtenteRepository;

@RestController
@RequestMapping("/api")
public class SmistamentoFatturaAUnUtenteRestController {
	
	@Autowired
	ApplicationConfigRepository applicationConfigRepo;
	
	@Autowired
	FatturaRepository fatturaRepo;
	
	@Autowired
	UtenteRepository utenteRepo;
	
	@GetMapping("spostamentoFatturaAUnUtente/utente-nome/{nomeUtente}/idFatturaInErrore/{idFattura}")
	Fattura spostamentoFatturaAUnUtente(@PathVariable("nomeUtente") String nomeUtente, @PathVariable("idFattura") Integer idFattura){
	    Utente utente = new Utente();
	    utente.setNome(nomeUtente);
	    utente.setFatturaId(idFattura);
	    utenteRepo.save(utente);
	    Fattura fatturaScelta = fatturaRepo.findOne(idFattura);
	    fatturaScelta.setStato("da controllare");
	    
	    File source = new File(fatturaScelta.getNomeFile());
	    File dest = new File("C:\\Users\\Francesco Altarelli\\Documents\\utenti\\"+nomeUtente);
	    try {
	        FileUtils.moveFileToDirectory(source, dest, true);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    fatturaRepo.save(fatturaScelta);
		return fatturaScelta;
	}

}
