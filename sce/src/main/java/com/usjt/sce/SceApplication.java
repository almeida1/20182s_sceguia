package com.usjt.sce;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SceApplication {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(SceApplication.class);
		SpringApplication.run(SceApplication.class, args);
		logger.info("aplicação executada com sucesso");
	}
}
