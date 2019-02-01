package it.objectmethod.smistatore;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
				System.out.println("copying " + path.toString());

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
					System.out.println("cliente trovato id: "+clienteTrovato.getId());

					subFolder= "\\"+clienteTrovato.getName();
					fattura.setIdCliente(clienteTrovato.getId());
					fattura.setStato(Stato.PROCESSED);;
				} else {
					subFolder= "\\scarti";
					fattura.setIdCliente(0);
					fattura.setStato(Stato.DISCARDED);

				}
				File destFile = new File(applicationConfigRepo.findValueBySearchedKey("path.output")+subFolder);
				destFile.mkdir();
				Path outputDirectory = destFile.toPath();
				Path d2 = outputDirectory.resolve(path.getFileName());	

				fattura.setNomeFile(d2.toString());

				System.out.println("destination File=" + d2);
				Files.move(path, d2, REPLACE_EXISTING);
				fatturaRepo.save(fattura);

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}


	}

}
