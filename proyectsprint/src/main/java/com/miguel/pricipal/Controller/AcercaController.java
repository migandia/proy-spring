package com.miguel.pricipal.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class AcercaController {
	
	@Value("${valor.secreto}")
	private String val;
	@RequestMapping ("/acerca")
	@ResponseBody
	public String obtenerNombre () {
		return "Angel Miguel Andia Maldonado: "+ val;
	}

}
