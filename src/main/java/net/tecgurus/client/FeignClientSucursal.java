package net.tecgurus.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.tecgurus.model.Sucursal;


@FeignClient(name = "DEMO-4-SUCURSAL", fallback = FeignFallbackSucursal.class)
public interface FeignClientSucursal {

	@RequestMapping(method = RequestMethod.GET, value =  "/sucursal/all")
	public ResponseEntity<List<Sucursal>> callRemoteSucursales();
	
	@RequestMapping(method = RequestMethod.POST, value = "/sucursal")
	ResponseEntity<Sucursal> remoteFeingCrearSucursal(@RequestBody Sucursal sucursal);
	
}
