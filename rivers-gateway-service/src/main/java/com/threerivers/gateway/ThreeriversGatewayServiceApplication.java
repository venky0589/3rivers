package com.threerivers.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ThreeriversGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreeriversGatewayServiceApplication.class, args);
	}

}
