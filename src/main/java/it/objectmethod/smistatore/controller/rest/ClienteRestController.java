package it.objectmethod.smistatore.controller.rest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.smistatore.model.Cliente;
import it.objectmethod.smistatore.model.Fattura;
import it.objectmethod.smistatore.model.Fattura.Stato;
import it.objectmethod.smistatore.repository.ApplicationConfigRepository;
import it.objectmethod.smistatore.repository.ClienteRepository;
import it.objectmethod.smistatore.repository.FatturaRepository;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	ApplicationConfigRepository applicationConfigRepo;
	
	@Autowired
	FatturaRepository fatturaRepo;
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@GetMapping("cliente/spostamentoFattura/{clienteId}/{fatturaId}")
	void findByStatus(@PathVariable("clienteId") Integer clienteId, @PathVariable("fatturaId") Integer fatturaId){
		Cliente cliente = clienteRepo.findOne(clienteId);
		Fattura fattura = fatturaRepo.findOne(fatturaId);
		File sourceDir = new File(fattura.getNomeFile());
		File destDir = new File(applicationConfigRepo.findValueBySearchedKey("path.output")+"\\"+cliente.getName());
		try {
			FileUtils.moveFileToDirectory(sourceDir, destDir, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		fattura.setIdCliente(clienteId);
		fattura.setStato(Stato.CHECK_REQ);
		fatturaRepo.save(fattura);
		
		
	}

}
