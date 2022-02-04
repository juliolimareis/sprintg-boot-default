package com.alerta.dc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ApiAlertaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAlertaApplication.class, args);
	}

}
