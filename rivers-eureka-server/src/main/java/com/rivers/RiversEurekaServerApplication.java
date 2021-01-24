package com.rivers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RiversEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiversEurekaServerApplication.class, args);
	}
}
