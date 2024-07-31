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
import com.miguel.pricipal.Exceptions.ProductoNoEncontradoException;
import com.miguel.principal.Models.Producto;

@Controller
public class ProductoController {
	@Autowired
	ObjectMapper objectMapper;
	
	private static Map<String, Producto> productos = new HashMap<>();
	static {
		Producto e1 = new Producto(1256, "Azucar",15.56);
		Producto e2 = new Producto(3258, "Cafe",20.50);
		Producto e3 = new Producto(9587, "Crema",13);
		productos.put("1", e1);
		productos.put("2", e2);
		productos.put("3", e3);
	}
	
	// http://localhost:9092/producto [GET]
	@GetMapping("/producto")
	public ResponseEntity<Object> getProducto(){
		return new ResponseEntity<>(productos.values(), HttpStatus.OK); // 200
	}
	// http://localhost:9092/producto [POST]
	@PostMapping("/producto")
	public ResponseEntity<Object> nuevoProducto(@RequestBody Producto est){
		productos.put(est.getCodigo()+"", est);
		URI ubicacionDelRecurso = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{codigo}").buildAndExpand(est.getCodigo()).toUri();

		return ResponseEntity.created(ubicacionDelRecurso).build();
	}
	// http://localhost:9092/producto/4 [PUT]
	@PutMapping("/producto/{identif}")
	public ResponseEntity<Object> modificarProducto(@PathVariable("identif") String iidd, @RequestBody Producto est ){
		if(!productos.containsKey(iidd))
			throw new ProductoNoEncontradoException();
		productos.remove(iidd);
		est.setCodigo(Integer.parseInt(iidd));
		productos.put(iidd, est);
		return new ResponseEntity<>("Se modifica al producto "+est.getCodigo(), HttpStatus.OK); // 201
	}
	// http://localhost:9092/producto/4 [DELETE]
	@DeleteMapping("/producto/{identif}")
	public ResponseEntity<Object> eliminarProducto(@PathVariable("identif") String iidd){
		productos.remove(iidd);
		return new ResponseEntity<>("Se elimina al producto "+iidd, HttpStatus.OK); // 200
	}
	

}

