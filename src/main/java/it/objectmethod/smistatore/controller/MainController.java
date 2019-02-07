package it.objectmethod.smistatore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("home")
	public String index(ModelMap model) {
		return "main";
	}

}
