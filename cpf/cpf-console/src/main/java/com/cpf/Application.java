package com.cpf;

import com.cpf.logger.BusinessLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class Application {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Application.class);
		BusinessLogger.infoLog("test",null,"success",null,logger);
		SpringApplication.run(Application.class, args);
	}
}
