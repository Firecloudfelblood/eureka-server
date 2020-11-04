package net.tecgurus.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.tecgurus.model.Sucursal;

@Component
public class FeignFallbackSucursal implements FeignClientSucursal{
	
	@Override
	public ResponseEntity<List<Sucursal>> callRemoteSucursales(){

		System.out.println("METODO DE FALLBACK sucursales" );
		Sucursal s = new Sucursal();
		s.setIdSucursal(0000);
		s.setNombre("Sucursal fallback");
		s.setDireccion("Falback");
		List<Sucursal> lista = new ArrayList<Sucursal>();
		lista.add(s);
		return new ResponseEntity<List<Sucursal>> (lista, HttpStatus.I_AM_A_TEAPOT);
	}

	@Override
	public ResponseEntity<Sucursal> remoteFeingCrearSucursal(Sucursal sucursal) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
