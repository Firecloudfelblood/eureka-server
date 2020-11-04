package net.tecgurus.client;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import net.tecgurus.model.Cliente;
import net.tecgurus.model.Sucursal;

@Service
public class DinamicClient {
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@HystrixCommand(fallbackMethod = "defaultFallbackSucursales")	
	public ResponseEntity<List<Sucursal>> dynamicCallSucursales(String url){
		
		//obtiene instancia del servicio
		ServiceInstance serviceInstance = loadBalancer.choose("DEMO-4-SUCURSAL");
		System.out.println("Serving from: "+serviceInstance.getUri());
		String baseUrl = serviceInstance.getUri()+url;//{host}:{port}/{path}/{to}/{service}
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Sucursal>> response = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept",  MediaType.APPLICATION_JSON_VALUE);
		
		try {
			response = restTemplate.exchange(
					baseUrl, //url a invocar
					HttpMethod.GET, //metodo http
					new HttpEntity<>(headers),//cabeceras
					new ParameterizedTypeReference<List<Sucursal>>() {}//tipo parameterizado de retorno
					
					
					);
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		return response;
		
	}
	
	public ResponseEntity<Sucursal> dynamicCallCrearSucursal(String url, Sucursal sucursal){
		ServiceInstance serviceInstance = loadBalancer.choose("DEMO-4-SUCURSAL");
		System.out.println("\n\n\n\n\nServing from: "+serviceInstance.getUri());
		String baseUrl = serviceInstance.getUri()+url;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Sucursal> response = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("nombre", sucursal.getNombre());
			jsonObj.put("direccion", sucursal.getDireccion());
		}catch(JSONException ex) {
			ex.printStackTrace();
		}
		try {
			HttpEntity<String> request = new HttpEntity<String>(jsonObj.toString(),headers);
			response = restTemplate.postForEntity(baseUrl, request, Sucursal.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return response;
		
		
	}
	
	public ResponseEntity<List<Cliente>> dynamicCallCLientes(String url){
		ServiceInstance serviceInstance = loadBalancer.choose("DEMO-3-CLIENTE");
		System.out.println("Served from: "+serviceInstance.getUri());
		String baseUrl = serviceInstance.getUri()+url;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Cliente>> response = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET,
					new HttpEntity<>(headers),
					new ParameterizedTypeReference<List<Cliente>>() {}
					);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return response;
		
	}

	private ResponseEntity<List<Sucursal>> defaultFallbackSucursales(String url){
		
		System.out.println("METODO DE FALLBACK sucursales" );
		Sucursal s = new Sucursal();
		s.setIdSucursal(0000);
		s.setNombre("Sucursal fallback");
		s.setDireccion("Falback");
		List<Sucursal> lista = new ArrayList<Sucursal>();
		lista.add(s);
		return new ResponseEntity<List<Sucursal>> (lista, HttpStatus.I_AM_A_TEAPOT);
		
	}
}
