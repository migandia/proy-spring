package com.miguel.pricipal.Controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguel.pricipal.Exceptions.ClienteNoEncontradoException;
import com.miguel.pricipal.Exceptions.EmpleadoNoEncontradoException;
import com.miguel.principal.Models.Cliente;

public class ClienteController {
	@Autowired
	ObjectMapper objectMapper;
	
	private static Map<String, Cliente> clientes = new HashMap<>();
	static {
		Cliente e1 = new Cliente(556897, "Lopez");
		Cliente e2 = new Cliente(698578, "Chura");
		Cliente e3 = new Cliente(345678, "Pereira");
		clientes.put("1", e1);
		clientes.put("2", e2);
		clientes.put("3", e3);
	}
	
	// http://localhost:9092/cliente [GET]
	@GetMapping("/cliente")
	public ResponseEntity<Object> getCliente(){
		return new ResponseEntity<>(clientes.values(), HttpStatus.OK); // 200
	}
	// http://localhost:9092/cliente [POST]
	@PostMapping("/cliente")
	public ResponseEntity<Object> nuevoCliente(@RequestBody Cliente est){
		clientes.put(est.getNit()+"", est);
		URI ubicacionDelRecurso = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{nit}").buildAndExpand(est.getNit()).toUri();
	
		return ResponseEntity.created(ubicacionDelRecurso).build();
	}
	// http://localhost:9092/cliente/4 [PUT]
	@PutMapping("/cliente/{identif}")
	public ResponseEntity<Object> modificarCliente(@PathVariable("identif") String iidd, @RequestBody Cliente est ){
		if(!clientes.containsKey(iidd))
			throw new ClienteNoEncontradoException();
		clientes.remove(iidd);
		est.setNit(Integer.parseInt(iidd));
		clientes.put(iidd, est);
		return new ResponseEntity<>("Se modifica al cliente "+est.getNit(), HttpStatus.OK); // 201
	}
	// http://localhost:9092/cliente/4 [DELETE]
	@DeleteMapping("/cliente/{identif}")
	public ResponseEntity<Object> eliminarCliente(@PathVariable("identif") String iidd){
		clientes.remove(iidd);
		return new ResponseEntity<>("Se elimina al cliente "+iidd, HttpStatus.OK); // 200
	}

}
