package it.objectmethod.smistatore.controller.rest;

import java.security.SecureRandom;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

	@PostMapping("/utente/login")  
	String login(@RequestBody Utente utenteJson){
		Utente utente = new Utente();
		int utenteId = 0;
		String token ="";  

		utente = utenteRepo.login(utenteJson.getUsername(), utenteJson.getPassword());

		if(utente!=null) {
			utenteId= utente.getId();

			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			token = bytes.toString();

			Map<String, Integer> map =  raccoltaToken.getRaccoltaToken();
			map.put(token, utenteId);  

		}


		return token;
	}

	@GetMapping("utente/isAdmin")
	public boolean isAdmin(@RequestHeader("Authorization") String token) {
		boolean isAdmin = false;
		Map<String, Integer> map = raccoltaToken.getRaccoltaToken();
		int utenteId=map.get(token);
		Utente utente = utenteRepo.findOne(utenteId);

		if(utente.isAdmin()) {
			isAdmin = true;
		}

		return isAdmin;
	}


}
