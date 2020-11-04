package net.tecgurus.model;



public class Cliente implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer idCliente;
	
	private String nombre;
	private String apaterno;
	private String amaterno;
	private Integer edad;
	
	
	
	
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApaterno() {
		return apaterno;
	}
	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}
	public String getAmaterno() {
		return amaterno;
	}
	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

