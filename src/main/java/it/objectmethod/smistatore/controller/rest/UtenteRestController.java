package it.objectmethod.smistatore.controller.rest;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/utente/login/{username}/{password}") //NO PASSWORD E UTENTE NELL'URL
	String login(@PathVariable("username") String username, @PathVariable("password") String password){
		Utente utente = new Utente();
		int utenteId = 0;
		String token ="login non riuscito"; //Nella variabile token ci vanno solo token

		utente = utenteRepo.login(username, password);

		if(utente!=null) {
			utenteId= utente.getId();

			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			token = bytes.toString();

			Map<Integer,String> map =  new HashMap<Integer,String>();
			if(raccoltaToken.getRaccoltaToken() == null) {
				map.put(utenteId, token); //usare token come chiave, id utente come value
				raccoltaToken.setRaccoltaToken(map);
			} else {
				map = raccoltaToken.getRaccoltaToken();
				map.put(utenteId, token);
				raccoltaToken.setRaccoltaToken(map);
			}
		}


		return token;
	}


}
