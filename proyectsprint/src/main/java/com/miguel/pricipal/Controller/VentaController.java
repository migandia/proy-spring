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
import com.miguel.pricipal.Exceptions.VentaNoEncontradaException;
import com.miguel.principal.Models.Venta;

@Controller
public class VentaController {
	@Autowired
	ObjectMapper objectMapper;
	
	private static Map<String, Venta> ventas = new HashMap<>();
	static {
		Venta e1 = new Venta(1, "Lupe","Lopez","Azucar",1);
		Venta e2 = new Venta(1, "Mayra","Chura","Cafe",1);
		Venta e3 = new Venta(1, "Jorge","Pereira","Crema",2);
		ventas.put("1", e1);
		ventas.put("2", e2);
		ventas.put("3", e3);
	}
	
	// http://localhost:9092/producto [GET]
	@GetMapping("/venta")
	public ResponseEntity<Object> getVenta(){
		return new ResponseEntity<>(ventas.values(), HttpStatus.OK); // 200
	}
	// http://localhost:9092/venta [POST]
	@PostMapping("/venta")
	public ResponseEntity<Object> nuevoProducto(@RequestBody Venta est){
		ventas.put(est.getId()+"", est);
		URI ubicacionDelRecurso = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(est.getId()).toUri();
		
		return ResponseEntity.created(ubicacionDelRecurso).build();
	}
	// http://localhost:9092/venta/4 [PUT]
	@PutMapping("/venta/{identif}")
	public ResponseEntity<Object> modificarVenta(@PathVariable("identif") String iidd, @RequestBody Venta est ){
		if(!ventas.containsKey(iidd))
			throw new VentaNoEncontradaException();
		ventas.remove(iidd);
		est.setId(Integer.parseInt(iidd));
		ventas.put(iidd, est);
		return new ResponseEntity<>("Se modifica al producto "+est.getId(), HttpStatus.OK); // 201
	}
	// http://localhost:9092/venta/4 [DELETE]
	@DeleteMapping("/venta/{identif}")
	public ResponseEntity<Object> eliminarVenta(@PathVariable("identif") String iidd){
		ventas.remove(iidd);
		return new ResponseEntity<>("Se elimina la venta "+iidd, HttpStatus.OK); // 200
	}
	

}

