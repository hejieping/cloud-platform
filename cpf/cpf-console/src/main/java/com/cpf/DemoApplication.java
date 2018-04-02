package com.cpf;

import com.cpf.logger.BusinessLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(DemoApplication.class);
		BusinessLogger.infoLog("test",null,"success",null,logger);
		SpringApplication.run(DemoApplication.class, args);
	}
}
