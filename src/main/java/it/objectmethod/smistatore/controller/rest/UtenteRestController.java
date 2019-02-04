package it.objectmethod.smistatore.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.smistatore.model.Fattura;
import it.objectmethod.smistatore.model.Utente;
import it.objectmethod.smistatore.repository.UtenteRepository;


@RestController
@RequestMapping("/api")
public class UtenteRestController {

	@Autowired
	UtenteRepository utenteRepo;
	
	@GetMapping("/utente/login/{username}/{password}")
	Utente login(@PathVariable("username") String username, @PathVariable("password") String password){
		return utenteRepo.login(username, password);
	}
	
	
}
