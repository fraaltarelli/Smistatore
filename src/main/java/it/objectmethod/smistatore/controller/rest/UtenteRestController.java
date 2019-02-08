package it.objectmethod.smistatore.controller.rest;

import java.security.SecureRandom;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.smistatore.model.RaccoltaToken;
import it.objectmethod.smistatore.model.Utente;
import it.objectmethod.smistatore.repository.UtenteRepository;


@RestController
@RequestMapping("/api")
public class UtenteRestController {

	@Autowired
	RaccoltaToken raccoltaToken;

	@Autowired
	UtenteRepository utenteRepo;

	@PostMapping("/utente/login") //NO PASSWORD E UTENTE NELL'URL
	String login(@RequestBody Utente utenteJson){
		Utente utente = new Utente();
		int utenteId = 0;
		String token =""; //Nella variabile token ci vanno solo token

		utente = utenteRepo.login(utenteJson.getUsername(), utenteJson.getPassword());

		if(utente!=null) {
			utenteId= utente.getId();

			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			token = bytes.toString();

			Map<String, Integer> map =  raccoltaToken.getRaccoltaToken();
			map.put(token, utenteId); //usare token come chiave, id utente come value
			
		}


		return token;
	}


}
