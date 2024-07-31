
package com.miguel.pricipal.Controller;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguel.pricipal.Exceptions.EmpleadoNoEncontradoException;

import com.miguel.principal.Models.Empleado;

@Controller
public class EmpleadoController {
	@Autowired
	ObjectMapper objectMapper;
	
	private static Map<String, Empleado> empleados = new HashMap<>();
	static {
		Empleado e1 = new Empleado(556897, "Lupe","Cazas","60175674","lupe@gmail.com");
		Empleado e2 = new Empleado(698578, "Mayra","Aramayo","70589642","mayra@gmail.com");
		Empleado e3 = new Empleado(345678, "Jorge","Soruco","71548952","jorge@gmail.com");
		empleados.put("1", e1);
		empleados.put("2", e2);
		empleados.put("3", e3);
	}
	
	// http://localhost:9092/empleados [GET]
	@GetMapping("/empleados")
	public ResponseEntity<Object> getEmpleado(){
		return new ResponseEntity<>(empleados.values(), HttpStatus.OK); // 200
	}
	// http://localhost:9092/empleados [POST]
	@PostMapping("/empleados")
	public ResponseEntity<Object> nuevoEmpleado(@RequestBody Empleado est){
		empleados.put(est.getCi()+"", est);
		URI ubicacionDelRecurso = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{ci}").buildAndExpand(est.getCi()).toUri();
		
		return ResponseEntity.created(ubicacionDelRecurso).build();
	}
	// http://localhost:9092/empleados/4 [PUT]
	@PutMapping("/empleados/{identif}")
	public ResponseEntity<Object> modificarEmpleado(@PathVariable("identif") String iidd, @RequestBody Empleado est ){
		if(!empleados.containsKey(iidd))
			throw new EmpleadoNoEncontradoException();
		empleados.remove(iidd);
		est.setCi(Integer.parseInt(iidd));
		empleados.put(iidd, est);
		return new ResponseEntity<>("Se modifica al empleado "+est.getCi(), HttpStatus.OK); // 201
	}
	// http://localhost:9092/empleados/4 [DELETE]
	@DeleteMapping("/empleados/{identif}")
	public ResponseEntity<Object> eliminarEmpleado(@PathVariable("identif") String iidd){
		empleados.remove(iidd);
		return new ResponseEntity<>("Se elimina al empleado "+iidd, HttpStatus.OK); // 200
	}
	

}

