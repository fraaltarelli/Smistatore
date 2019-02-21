package it.objectmethod.smistatore.controller.rest;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.objectmethod.smistatore.TransactionFilter;
import it.objectmethod.smistatore.jaxb.FatturaElettronicaMain;
import it.objectmethod.smistatore.jaxb.model.FatturaElettronica;
import it.objectmethod.smistatore.model.Cliente;
import it.objectmethod.smistatore.model.Fattura;
import it.objectmethod.smistatore.model.Fattura.Stato;
import it.objectmethod.smistatore.model.RaccoltaToken;
import it.objectmethod.smistatore.repository.ApplicationConfigRepository;
import it.objectmethod.smistatore.repository.ClienteRepository;
import it.objectmethod.smistatore.repository.FatturaRepository;
import it.objectmethod.smistatore.repository.UtenteRepository;


@RestController
@RequestMapping("/api")
public class FatturaRestController {


	@Autowired
	FatturaRepository fatturaRepo;

	@Autowired
	UtenteRepository utenteRepo;

	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	RaccoltaToken raccoltaToken;

	@Autowired
	FatturaElettronicaMain fatturaElettronicaMain;

	@Autowired
	ApplicationConfigRepository applicationConfigRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionFilter.class);

	String subFolder = "\\discarded";


	@RequestMapping(value="fattura/scarica/{idFattura}/{idCliente}", method = RequestMethod.GET)
	public void downloadFile(
			@PathVariable("idFattura") Integer idFattura, @PathVariable("idCliente") Integer idCliente,
			HttpServletResponse response) throws IOException {

		try {
			Fattura fattura = fatturaRepo.findOne(idFattura);
			String nomeFileXml = fattura.getNomeFile();

			String outputDirectory = applicationConfigRepo.findValueBySearchedKey("path.output");
			if(idCliente!=0) {
				subFolder="\\"+idCliente;
			}
			String pathXml = outputDirectory+subFolder+"\\"+nomeFileXml;


			File file = new File(pathXml);
			InputStream is = new FileInputStream(file);
			byte[] bytes = IOUtils.toByteArray(is);
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes);

			response.flushBuffer();
			out.close();
			out.flush();


		} catch (IOException ex) {
			LOGGER.debug("inside download get catch");
			throw new RuntimeException("Can't download the file");
		}
	}




	@RequestMapping(value="fattura/by-xsl/{idFattura}/{idCliente}/{foglioDiStile}", method = RequestMethod.GET)
	public void fatturaHtmlDaFoglioDiStile(
			@PathVariable("idFattura") Integer idFattura, @PathVariable("idCliente") Integer idCliente,
			@PathVariable("foglioDiStile") String foglioDiStile, HttpServletResponse response) throws IOException {

		try {
			Fattura fattura = fatturaRepo.findOne(idFattura);
			String nomeFileXml = fattura.getNomeFile();

			String outputDirectory = applicationConfigRepo.findValueBySearchedKey("path.output");

			if(idCliente!=0) {
				subFolder="\\"+idCliente;
			}
			String pathXml = outputDirectory+subFolder+"\\"+nomeFileXml;


			TransformerFactory factory = TransformerFactory.newInstance();
			Templates template = factory.newTemplates(new StreamSource(
					new FileInputStream(outputDirectory+"\\"+foglioDiStile+".xsl")));
			Transformer xformer = template.newTransformer();

			Source source = new StreamSource(new FileInputStream(pathXml));
			ServletOutputStream out = response.getOutputStream();
			Result result = new StreamResult(out);

			xformer.transform(source, result);

			response.flushBuffer();
			out.close();
			out.flush();

		} catch (Exception e) {
			LOGGER.debug("errore apertura fattura con foglio di stile");
		}


	}




	@GetMapping("/fattura/rifiuta/{idFattura}")
	void rifiutaFattura(@PathVariable("idFattura") Integer idFattura) {
		Fattura fattura = fatturaRepo.findOne(idFattura);
		Cliente cliente = fattura.getCliente();
		Integer idCliente = cliente.getId();
		String nomeFileXml = fattura.getNomeFile();
		fattura.setStato(Stato.REFUSED);
		fattura.setCliente(null);
		fatturaRepo.save(fattura);

		String outputDirectory = applicationConfigRepo.findValueBySearchedKey("path.output");
		String inputPath = outputDirectory+"\\"+idCliente+"\\"+nomeFileXml;
		String outputPath = outputDirectory+"\\discarded\\"+nomeFileXml; 
		Path input = Paths.get(inputPath);
		Path output = Paths.get(outputPath);
		try {
			Files.move(input, output, REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@GetMapping("/fattura/processa/{idFattura}")
	void processaFattura(@PathVariable("idFattura") Integer idFattura) {
		Fattura fattura = fatturaRepo.findOne(idFattura);
		fattura.setStato(Stato.PROCESSED);
		fatturaRepo.save(fattura);
	}



	@PostMapping("/fattura/fattureId-to-fattureNomeFile")
	String[] idFatturaToNomeFile(@RequestBody Integer[] fattureId) {
		String[] fattureNomeFile = new String[fattureId.length];

		for(int i=0; i<fattureId.length; i++) {
			Fattura fattura = fatturaRepo.findOne(fattureId[i]);
			fattureNomeFile[i] = fattura.getNomeFile();
		}

		return fattureNomeFile;
	}




	@RequestMapping(method = RequestMethod.POST, value = "/fattura/verificaFileCaricato")
	@ResponseBody
	String verificaFatturaCaricata(@RequestParam("file") MultipartFile fatturaCaricata,
			@RequestHeader("Authorization") String token) {

		String messaggio = "errore nel caricamento della fattura";

		Map<String, Integer> map = raccoltaToken.getRaccoltaToken();
		int utenteId=map.get(token);
		Cliente cliente = utenteRepo.findClienteFromId(utenteId);

		InputStream is=null;
		try {
			is = fatturaCaricata.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FatturaElettronica entity = fatturaElettronicaMain.unmarshalFattura(is);

		Fattura fattura = new Fattura();
		Cliente clienteTrovato=null;
		String partitaIva = null;
		String codiceFiscale = null;
		String dataDocumento = null;
		Integer numeroDocumento = 0;

		try {
			partitaIva = entity.getFatturaElettronicaHeader().getCedentePrestatore().getDatiAnagrafici().getIdFiscaleIva().getIdCodice();
			codiceFiscale = entity.getFatturaElettronicaHeader().getCedentePrestatore().getDatiAnagrafici().getCodiceFiscale();
			dataDocumento = entity.getFatturaElettronicaBody().getDatiGenerali().getDatiGeneraliDocumento().getData();
			numeroDocumento = entity.getFatturaElettronicaBody().getDatiGenerali().getDatiGeneraliDocumento().getNumero();
		} catch (Exception e) {
			LOGGER.debug("errore nel recupero dei dati da jaxb.model in fattura rest controller");
			e.printStackTrace();
		}


		fattura.setNumeroDocumento(numeroDocumento);
		fattura.setDataDocumento(dataDocumento);

		clienteTrovato = clienteRepo.findOneBySearchedVatNumber(partitaIva);


		if(clienteTrovato == null) {
			clienteTrovato = clienteRepo.findOneBySearchedFiscalCode(codiceFiscale);
		}


		if(clienteTrovato!=null) {

			if(clienteTrovato.equals(cliente)) {
				subFolder= "\\"+clienteTrovato.getId();

				fattura.setCliente(clienteTrovato);
				fattura.setStato(Stato.SENT);


				String directoryCliente = applicationConfigRepo.findValueBySearchedKey("path.output")+subFolder;

				File directory = new File(directoryCliente);
				directory.mkdir();

				String fileOutput = directoryCliente+"\\"+fatturaCaricata.getOriginalFilename();
				File fatturaFile = new File(fileOutput);


				try {
					fatturaCaricata.transferTo(fatturaFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}


				fattura.setNomeFile(fatturaFile.getName());
				fatturaRepo.save(fattura);

				LOGGER.debug("fattura caricata");
				messaggio = "fattura caricata";
			}
			else {
				LOGGER.debug("cliente non associato alla fattura");
				messaggio = "cliente non associato alla fattura";
			}

		} 

		else {
			LOGGER.debug("cliente non trovato");
			messaggio = "cliente non trovato";

		}
		return messaggio;
	}






	@GetMapping("/fattura/ritornaFatture/isAdmin-statoFattura/{isAdmin}/{statoFattura}")
	List<Fattura> findByIdCliente(@PathVariable("isAdmin") Boolean isAdmin, @PathVariable("statoFattura") String statoFattura, 
			@RequestHeader("Authorization") String token){
		Map<String, Integer> map = raccoltaToken.getRaccoltaToken();
		int utenteId=map.get(token);
		Cliente cliente = utenteRepo.findClienteFromId(utenteId);
		List<Fattura> list = new ArrayList<Fattura>();
		Stato stato = null;
		if (!statoFattura.equals("null")) {
			stato = Stato.valueOf(statoFattura);
		}
		if(isAdmin == false) {
			if(stato==null) {
				list = fatturaRepo.findBycliente(cliente);
			}
			else {
				list = fatturaRepo.findByStatoFatturaCliente(stato, cliente);
			}
		}
		else {
			if(stato==null) {
				list= fatturaRepo.findAll();
			}
			else {
				list = fatturaRepo.findByStatoFattura(stato);
			}
		}
		return list;
	}

}
