package com.miguel.principal.Models;

public class Venta {
	 
	 private int id;
	 private String emp;
	 private String clien;
	 private String prod;
	 private int cantidad;
	 
		
		public Venta() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Venta(int id, String emp, String clien,String prod,int cantidad ) {
			super();
			this.id= id;
			this.emp = emp;
			this.prod = clien;
			this.prod = prod;
			this.cantidad = cantidad;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getEmp() {
			return emp;
		}
		public void setEmp(String emp) {
			this.emp = emp;
		}
		public String getClien() {
			return clien;
		}
		public void setClien(String clien) {
			this.clien = clien;
		}
		public String getProd() {
			return prod;
		}
		public void setProd(String prod) {
			this.prod = prod;
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

}
