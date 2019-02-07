package it.objectmethod.smistatore;

import java.io.IOException;

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
//		if(utenteRest.bLogin==false) {
//			
//		}
		String auth = ((HttpServletRequest) request).getHeader("Authorization");
		if(auth!=null && raccoltaToken.getRaccoltaToken()!= null) {
			if(raccoltaToken.getRaccoltaToken().containsValue(auth)) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			LOGGER.debug("Starting a transaction for req : {}", req.getRequestURI());
			LOGGER.debug("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
			chain.doFilter(request, response);
			LOGGER.debug("Committing a transaction for req : {}", req.getRequestURI());
			LOGGER.debug("Logging Response :{}",  res.getContentType());
			}
		}
		else if(auth==null){
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			LOGGER.debug("Starting a transaction for req : {}", req.getRequestURI());
			LOGGER.debug("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
			chain.doFilter(request, response);
			LOGGER.debug("Committing a transaction for req : {}", req.getRequestURI());
			LOGGER.debug("Logging Response :{}",  res.getContentType());
		}
		else {
			LOGGER.debug(((HttpServletRequest) request).getRequestURI().trim());
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}