package it.objectmethod.smistatore.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RaccoltaToken {

	
	public Map<String, Integer> raccoltaToken;

	public Map<String, Integer> getRaccoltaToken() {
		if(raccoltaToken==null) {
			return new HashMap<String, Integer>();
		}
		return raccoltaToken;
	}

	
}
