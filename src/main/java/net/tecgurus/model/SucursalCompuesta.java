package net.tecgurus.model;

import java.util.List;

public class SucursalCompuesta {
	private Sucursal sucursal;
	private List<Cliente> clientes;
	
	
	
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	

}
