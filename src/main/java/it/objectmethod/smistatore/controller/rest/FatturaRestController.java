package it.objectmethod.smistatore.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.smistatore.model.Cliente;
import it.objectmethod.smistatore.model.Fattura;
import it.objectmethod.smistatore.model.Utente;
import it.objectmethod.smistatore.model.Fattura.Stato;
import it.objectmethod.smistatore.model.RaccoltaToken;
import it.objectmethod.smistatore.repository.FatturaRepository;
import it.objectmethod.smistatore.repository.UtenteRepository;


@RestController
@RequestMapping("/api")
public class FatturaRestController {

	@Autowired
	FatturaRepository fatturaRepo;

	@Autowired
	UtenteRepository utenteRepo;

	@Autowired
	RaccoltaToken raccoltaToken;

	@GetMapping("/fattura/find-by-searchedStatus/{status}")
	List<Fattura> findByStatus(@PathVariable("status") String status){

		Stato stato = Stato.valueOf(status);
		return fatturaRepo.findByStatoFattura(stato);
	}

	@GetMapping("/fattura/ritornaFatture/isAdmin-statoFattura/{isAdmin}/{statoFattura}")
	List<Fattura> findByIdCliente(@PathVariable("isAdmin") Boolean isAdmin, @PathVariable("statoFattura") String statoFattura, 
			@RequestHeader("Authorization") String token){
		Map<String, Integer> map = raccoltaToken.getRaccoltaToken();
		int utenteId=map.get(token);
		Cliente cliente = utenteRepo.findClienteFromId(utenteId);
		List<Fattura> list = new ArrayList<Fattura>();
		Stato stato = null;
		if (!statoFattura.equals("null"))
			stato = Stato.valueOf(statoFattura);
		if(isAdmin == false) {
			if(stato==null) {
				list = fatturaRepo.findBycliente(cliente);
			}
			else {
				list = fatturaRepo.findByStatoFatturaCliente(stato, cliente);
			}
		}
		else {
			if(stato==null) {
				list= fatturaRepo.findAll();
			}
			else {
				list = fatturaRepo.findByStatoFattura(stato);
			}
		}
		return list;
	}

}
