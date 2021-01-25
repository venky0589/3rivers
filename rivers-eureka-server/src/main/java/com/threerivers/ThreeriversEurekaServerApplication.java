package com.threerivers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ThreeriversEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreeriversEurekaServerApplication.class, args);
	}
}
