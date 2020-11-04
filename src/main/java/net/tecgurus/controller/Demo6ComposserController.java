package net.tecgurus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.tecgurus.client.DinamicClient;
import net.tecgurus.client.FeignClientCliente;
import net.tecgurus.client.FeignClientSucursal;
import net.tecgurus.model.Cliente;
import net.tecgurus.model.Sucursal;
import net.tecgurus.model.SucursalCompuesta;

@RestController
public class Demo6ComposserController {
	@Autowired
	private DinamicClient dynamicClient;
	
	@Autowired
	private FeignClientSucursal feingClientScursal;
	
	@Autowired
	private FeignClientCliente feignClientCliente;
	
	@GetMapping("/sucursalcompuesta/all")
	public ResponseEntity<List<SucursalCompuesta>> consultarSucursalesYClientes(){
		
		ResponseEntity<List<Sucursal>> responseSucursales =
//				dynamicClient.dynamicCallSucursales("/sucursal/all");
				feingClientScursal.callRemoteSucursales();
		
		System.out.println("Response demo 4 Status: "+responseSucursales.getStatusCode());
//		if(responseSucursales.getStatusCode() != HttpStatus.OK) {
//			return new ResponseEntity<List<SucursalCompuesta>>(HttpStatus.I_AM_A_TEAPOT);
//		}
		
		List<SucursalCompuesta> listaSc = new ArrayList<SucursalCompuesta>();
		for (Sucursal s : responseSucursales.getBody()) {
			SucursalCompuesta sc = new SucursalCompuesta();
			sc.setSucursal(s);
			listaSc.add(sc);
		}
		
//		cliete usando resttemplate
//		ResponseEntity<List<Cliente>> responseClientes =
//				dynamicClient.dynamicCallCLientes("/clientes");
		//cliente usando feign
		ResponseEntity<List<Cliente>> responseClientes =
				feignClientCliente.remoteFeingClientes();
		
		System.out.println("Response demo 3 Status: "+responseClientes.getStatusCode());
		
		if(responseClientes.getStatusCode() != HttpStatus.OK) {
			return new ResponseEntity<List<SucursalCompuesta>>(listaSc, HttpStatus.FAILED_DEPENDENCY);
		}
		for(SucursalCompuesta sc: listaSc) {
			sc.setClientes(responseClientes.getBody());
		}
		
		return new ResponseEntity<List<SucursalCompuesta>>(listaSc, HttpStatus.OK);
		
	}
	
	@PostMapping("/sucursalCompuesta")
	public ResponseEntity<Sucursal> crearSucursalRemota(@RequestBody Sucursal sucursal){
		return dynamicClient.dynamicCallCrearSucursal("/sucursal", sucursal);
	}
	
	

}
