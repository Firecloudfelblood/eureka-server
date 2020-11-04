package net.tecgurus.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.tecgurus.model.Cliente;
@FeignClient("DEMO-3-CLIENTE")
public interface FeignClientCliente {
	@RequestMapping(method = RequestMethod.GET,
			value= "/clientes")
	ResponseEntity<List<Cliente>> remoteFeingClientes();

}
