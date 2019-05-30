package com.supermercado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

	/**
	 * Spring Boot Launch
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}
