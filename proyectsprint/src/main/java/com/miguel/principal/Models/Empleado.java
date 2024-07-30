package com.miguel.principal.Models;

public class Empleado {

	
	private int ci;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	public Empleado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Empleado(int ci, String nombre, String apellido,String telefono,String email) {
		super();
		this.ci= ci;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
	}
	public int getCi() {
		return ci;
	}
	public void setCi(int ci) {
		this.ci = ci;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
