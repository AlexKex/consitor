package ru.apr.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ConsitorApplication {

	public static Logger logger = LoggerFactory.getLogger(ConsitorApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ConsitorApplication.class);
		app.run(args);
	}
}
