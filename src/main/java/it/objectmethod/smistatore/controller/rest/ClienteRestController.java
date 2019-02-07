package it.objectmethod.smistatore.controller.rest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.smistatore.TransactionFilter;
import it.objectmethod.smistatore.model.Cliente;
import it.objectmethod.smistatore.model.Fattura;
import it.objectmethod.smistatore.model.Fattura.Stato;
import it.objectmethod.smistatore.repository.ApplicationConfigRepository;
import it.objectmethod.smistatore.repository.ClienteRepository;
import it.objectmethod.smistatore.repository.FatturaRepository;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteRestController.class);

	@Autowired
	ApplicationConfigRepository applicationConfigRepo;

	@Autowired
	FatturaRepository fatturaRepo;

	@Autowired
	ClienteRepository clienteRepo;

	@GetMapping("/cliente/spostamentoFattura/{clienteNome}/{fatturaNome}")
	String spostamentoFattura(@PathVariable("clienteNome") String clienteNome, @PathVariable("fatturaNome") String fatturaNome){
		Cliente cliente = clienteRepo.findByName(clienteNome);
		Fattura fattura = fatturaRepo.findBynomeFile(fatturaNome);
		String messaggio = "spostamento fattura non eseguibile";

		if(cliente!=null && fattura!=null) {
			if(fattura.getStato()==Stato.DISCARDED) {
				File sourceDir = new File(applicationConfigRepo.findValueBySearchedKey("path.output")+"\\scarti\\"+fattura.getNomeFile());
				File destDir = new File(applicationConfigRepo.findValueBySearchedKey("path.output")+"\\"+cliente.getName());
				try {
					FileUtils.moveFileToDirectory(sourceDir, destDir, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				fattura.setIdCliente(cliente.getId());
				fattura.setStato(Stato.CHECK_REQ);
				fatturaRepo.save(fattura);
				LOGGER.debug("Spostamento fattura riuscito");
				messaggio = "spostamento fattura riuscito";
			}
			else {
				LOGGER.debug("Spostamento fattura non eseguibile");
			}
		}

		else {
			LOGGER.debug("Spostamento fattura non eseguibile");
		}

		return messaggio;


	}

}
