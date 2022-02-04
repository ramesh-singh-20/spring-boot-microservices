package com.alphacoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TradeConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeConfigServerApplication.class, args);
	}

}
