package com.miguel.principal.Models;

public class Cliente {

	
	private int nit;
	private String apellido;
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(int nit,String apellido) {
		super();
		this.nit= nit;
		this.apellido = apellido;
	}
	public int getNit() {
		return nit;
	}
	public void setNit(int nit) {
		this.nit = nit;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	

}
