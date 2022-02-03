package com.alphacoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TradingAppServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingAppServiceRegistryApplication.class, args);
	}

}
