package it.objectmethod.smistatore;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import it.objectmethod.smistatore.service.LoggingService;

@Component
@Order
public class TransactionFilter implements Filter {

	@Override
	public void doFilter(
			ServletRequest request, 
			ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
        LoggingService myserv = new LoggingService();
        myserv.doStuff(req.getRequestURI());
		System.out.println("passato per il filtro,  "+req.getRequestURI());
		chain.doFilter(request, response);
		System.out.println("Committing a transaction for req : {}"+ req.getRequestURI());
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}