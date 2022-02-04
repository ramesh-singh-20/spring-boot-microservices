package com.alphacoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EkartConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkartConfigServerApplication.class, args);
	}

}
