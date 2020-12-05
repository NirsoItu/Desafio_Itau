package com.itau.desafio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApplication {

	private static Logger logger = LoggerFactory.getLogger(DesafioApplication.class);

	public static void main(String[] args) {

		logger.info("Iniciando a API Itaú Unibanco - Desafio de Programação");
		SpringApplication.run(DesafioApplication.class, args);
		logger.info("API iniciada e pronta para receber requisições");
	}

}
