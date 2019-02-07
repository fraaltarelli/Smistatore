package it.objectmethod.smistatore;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import it.objectmethod.smistatore.controller.MainController;
import it.objectmethod.smistatore.controller.rest.UtenteRestController;
import it.objectmethod.smistatore.model.RaccoltaToken;


@Component
@Order
public class TransactionFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionFilter.class);

	@Autowired
	UtenteRestController utenteRest;

	@Autowired
	RaccoltaToken raccoltaToken;

	@Override
	public void doFilter(
			ServletRequest request, 
			ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {	
		String uri = ((HttpServletRequest) request).getRequestURI();
		//aggiungere come eccezione url di login
//		if(uri.equals("/api/utente/login")) {
//			LOGGER.debug("Starting a transaction for req : {}", uri);  
//			chain.doFilter(request, response);
//			LOGGER.debug("Committing a transaction for req : {}", uri);
//		}
		if(uri.startsWith("api/cliente/spostamentoFattura/") || uri.startsWith("api/fattura/find-by-searchedStatus/")) {
			String auth = ((HttpServletRequest) request).getHeader("Authorization");
			//check su url, controllo poi il token che mi sono passato e in base a quello decido se andare avanti o meno.
			Map <String, Integer> map = raccoltaToken.getRaccoltaToken();
			if(auth!=null && map.containsKey(auth)) {
				LOGGER.debug("Starting a transaction for req : {}", uri);  
				chain.doFilter(request, response);
				LOGGER.debug("Committing a transaction for req : {}", uri);
			}
			else {
				destroy();
			}
		} else {
		
		LOGGER.debug("Starting a transaction for req : {}", uri);  
		chain.doFilter(request, response);
		LOGGER.debug("Committing a transaction for req : {}", uri);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}