package it.objectmethod.smistatore.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.smistatore.model.Fattura;
import it.objectmethod.smistatore.repository.FatturaRepository;


@RestController
@RequestMapping("/api")
public class ListaFattureInErroreOSmistateRestController {

	@Autowired
	FatturaRepository fatturaRepo;
	
	@GetMapping("fattura/find-by-searchedStatus/{status}")
	List<Fattura> findByStatus(@PathVariable("status") String status){
		return fatturaRepo.findBySearchedStatus(status);
	}
}
