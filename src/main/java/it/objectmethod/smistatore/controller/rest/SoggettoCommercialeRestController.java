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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.smistatore.PathUtil;
import it.objectmethod.smistatore.model.Fattura;
import it.objectmethod.smistatore.model.Fattura.Stato;
import it.objectmethod.smistatore.model.SoggettoCommerciale;
import it.objectmethod.smistatore.repository.ApplicationConfigRepository;
import it.objectmethod.smistatore.repository.FatturaRepository;
import it.objectmethod.smistatore.repository.SoggettoCommercialeRepository;

@RestController
@RequestMapping("/api")
public class SoggettoCommercialeRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SoggettoCommercialeRestController.class);

	@Autowired
	ApplicationConfigRepository applicationConfigRepo;

	@Autowired
	FatturaRepository fatturaRepo;

	@Autowired
	SoggettoCommercialeRepository scRepo;

	@Autowired
	PathUtil pathUtil;


	@GetMapping("/sc/spostamentoFattura/{scId}/{fatturaId}")
	String spostamentoFattura(@PathVariable("scId") Integer scId, @PathVariable("fatturaId") Integer fatturaId){

		SoggettoCommerciale sc = scRepo.findOne(scId);
		Fattura fattura = fatturaRepo.findOne(fatturaId);
		String messaggio = "spostamento fattura "+fattura.getNomeFile()+" al Soggetto Commerciale "+sc.getDenominazione()+" non eseguibile";


		if(sc!=null && fattura!=null) {

			if(fattura.getStato()==Stato.DISCARDED) {

				File sourceDir = new File(pathUtil.ritornaPath(applicationConfigRepo.findValueBySearchedKey("path.output"), "discarded", fattura.getNomeFile()));

				File destDir = new File(pathUtil.ritornaPath(applicationConfigRepo.findValueBySearchedKey("path.output"), ""+scId));

				try {
					FileUtils.moveFileToDirectory(sourceDir, destDir, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				fattura.setSoggCommerciale(sc);
				fattura.setStato(Stato.CHECK_REQ);
				fatturaRepo.save(fattura);

				LOGGER.debug("Spostamento fattura "+fattura.getNomeFile()+" al Soggetto Commerciale "+sc.getDenominazione()+" riuscito");
				messaggio = "spostamento fattura "+fattura.getNomeFile()+" al Soggetto Commerciale "+sc.getDenominazione()+" riuscito";
			}
			else {
				LOGGER.debug("Spostamento fattura "+fattura.getNomeFile()+" al Soggetto Commerciale "+sc.getDenominazione()+" non eseguibile");
			}
		}

		else {
			LOGGER.debug("Spostamento fattura non eseguibile");
		}

		return messaggio;
	}



	@PostMapping("/sc/by-fattura")
	public SoggettoCommerciale byFattura(@RequestBody Fattura fattura) {

		SoggettoCommerciale sc = fattura.getSoggCommerciale();
		return sc;
	}

	@GetMapping("/sc/by-searchedName/{scNome}")
	public List<SoggettoCommerciale> bySearchedName(@PathVariable("scNome") String scNome){

		return scRepo.findBySearchedName(scNome);
	}

}
