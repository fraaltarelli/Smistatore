package it.objectmethod.smistatore.model;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RaccoltaToken {

	
	Map<Integer, String> raccoltaToken;

	public Map<Integer, String> getRaccoltaToken() {
		return raccoltaToken;
	}

	public void setRaccoltaToken(Map<Integer, String> raccoltaToken) {
		this.raccoltaToken = raccoltaToken;
	}

	
}
