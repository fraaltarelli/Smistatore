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

import it.objectmethod.smistatore.model.Cliente;
import it.objectmethod.smistatore.model.Fattura;
import it.objectmethod.smistatore.model.Fattura.Stato;
import it.objectmethod.smistatore.model.UserHandlerReturnEntity;
import it.objectmethod.smistatore.repository.ApplicationConfigRepository;
import it.objectmethod.smistatore.repository.ClienteRepository;
import it.objectmethod.smistatore.repository.FatturaRepository;

@Component
public class SmistatoreScheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmistatoreScheduler.class);

	@Autowired
	ApplicationConfigRepository applicationConfigRepo;

	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	FatturaRepository fatturaRepo;

	@Autowired
	SaxParser sp;


	@Scheduled(fixedDelay = 10000)
	public void scheduleFixedDelayTask() {
		String subFolder="";

		Path inputDirectory = Paths.get(applicationConfigRepo.findValueBySearchedKey("path.input"));

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(inputDirectory)) {
			for (Path path : directoryStream) {
				LOGGER.debug("copying " + path.toString());

				Fattura fattura = new Fattura();
				Cliente clienteTrovato=null;
				UserHandlerReturnEntity entity = sp.leggiXml(path.toString());


				String partitaIva = entity.getPartitaIva();
				String codiceFiscale = entity.getCodiceFiscale();
				String dataDocumento = entity.getDataDocumento();
				Integer numeroDocumento = entity.getNumeroDocumento();
				fattura.setNumeroDocumento(numeroDocumento);
				fattura.setDataDocumento(dataDocumento);

				clienteTrovato = clienteRepo.findOneBySearchedVatNumber(partitaIva);

				if(clienteTrovato==null) {
					clienteTrovato = clienteRepo.findOneBySearchedFiscalCode(codiceFiscale);
				}


				if(clienteTrovato!=null) {
					LOGGER.debug("cliente trovato id: "+clienteTrovato.getId());

					subFolder= "\\"+clienteTrovato.getId();
					fattura.setCliente(clienteTrovato);
					fattura.setStato(Stato.PROCESSED);;
				} else {
					subFolder= "\\scarti";
					fattura.setCliente(null);
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
