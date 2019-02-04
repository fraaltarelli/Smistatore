package it.objectmethod.smistatore;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order
public class TransactionFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionFilter.class);

	@Override
	public void doFilter(
			ServletRequest request, 
			ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		LOGGER.debug("passato per il filtro,  "+req.getRequestURI());
		chain.doFilter(request, response);
		LOGGER.debug("Committing a transaction for req : {}"+ req.getRequestURI());
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}