package it.objectmethod.smistatore;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.objectmethod.smistatore.jaxb.FatturaElettronicaMain;
import it.objectmethod.smistatore.jaxb.model.FatturaElettronica;
import it.objectmethod.smistatore.model.Fattura;
import it.objectmethod.smistatore.model.Fattura.Stato;
import it.objectmethod.smistatore.model.SoggettoCommerciale;
import it.objectmethod.smistatore.repository.ApplicationConfigRepository;
import it.objectmethod.smistatore.repository.FatturaRepository;
import it.objectmethod.smistatore.repository.SoggettoCommercialeRepository;

@Component
public class SmistatoreScheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmistatoreScheduler.class);

	@Autowired
	ApplicationConfigRepository applicationConfigRepo;

	@Autowired
	SoggettoCommercialeRepository scRepo;

	@Autowired
	FatturaRepository fatturaRepo;

	@Autowired
	FatturaElettronicaMain fatturaElettronicaMain;


	@Scheduled(fixedDelay = 10000)
	public void scheduleFixedDelayTask() {
		String subFolder="";

		Path inputDirectory = Paths.get(applicationConfigRepo.findValueBySearchedKey("path.input"));

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(inputDirectory)) {
			for (Path path : directoryStream) {
				LOGGER.debug("copying " + path.toString());

				Fattura fattura = new Fattura();
				SoggettoCommerciale scTrovato = null;

				FatturaElettronica entity = fatturaElettronicaMain.unmarshalFattura(path.toString());

				String partitaIva = null;
				String codiceFiscale = null;
				String dataDocumento = null;
				Integer numeroDocumento = 0;

				try {
					partitaIva = entity.getFatturaElettronicaHeader().getCessionarioCommittente().getDatiAnagrafici().getIdFiscaleIva().getIdCodice();
					codiceFiscale = entity.getFatturaElettronicaHeader().getCessionarioCommittente().getDatiAnagrafici().getCodiceFiscale();
					dataDocumento = entity.getFatturaElettronicaBody().getDatiGenerali().getDatiGeneraliDocumento().getData();
					numeroDocumento = entity.getFatturaElettronicaBody().getDatiGenerali().getDatiGeneraliDocumento().getNumero();
				} catch (Exception e) {
					LOGGER.debug("errore nel recupero dei dati da jaxb.model in smistatore scheduler");
					e.printStackTrace();
				}

				fattura.setNumeroDocumento(numeroDocumento);
				fattura.setDataDocumento(dataDocumento);

				scTrovato = scRepo.findOneBySearchedVatNumber(partitaIva);

				if(scTrovato==null) {
					scTrovato = scRepo.findOneBySearchedFiscalCode(codiceFiscale);
				}


				if(scTrovato!=null) {
					LOGGER.debug("cliente trovato id: "+scTrovato.getId());

					subFolder= "\\"+scTrovato.getId();
					fattura.setSoggCommerciale(scTrovato);
					fattura.setStato(Stato.PROCESSED);;
				} else {
					subFolder= "\\discarded";
					fattura.setSoggCommerciale(null);
					fattura.setStato(Stato.DISCARDED);

				}
				File destFile = new File(applicationConfigRepo.findValueBySearchedKey("path.output")+subFolder);
				destFile.mkdir();
				Path outputDirectory = destFile.toPath();
				Path d2 = outputDirectory.resolve(path.getFileName());	

				fattura.setNomeFile(FilenameUtils.getName(d2.toString()));

				LOGGER.debug("destination File=" + d2);
				Files.move(path, d2, REPLACE_EXISTING);
				fatturaRepo.save(fattura);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
