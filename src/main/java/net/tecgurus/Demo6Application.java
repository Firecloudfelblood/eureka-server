package net.tecgurus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Lazy;

import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class Demo6Application {
	
	@Autowired
	@Lazy
	private EurekaClient eurekaClientl;
	
	@Value("${springapplication,name")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(Demo6Application.class, args);
	}

}
