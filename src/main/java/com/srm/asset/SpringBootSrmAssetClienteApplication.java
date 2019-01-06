package com.srm.asset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringBootSrmAssetClienteApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootSrmAssetClienteApplication.class);
	
	/**
	 * Inicialização do Spring-Boot
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Inicializando o Spring-Boot");
		SpringApplication.run(SpringBootSrmAssetClienteApplication.class, args);
	}
}
