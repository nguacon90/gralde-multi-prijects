package com.nguacon.platform;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Created by minh on 14/11/2017.
 */
@ComponentScan(basePackages = {"com.nguacon"})
@SpringBootApplication
public class Application
{
	private static final Logger LOGGER = Logger.getLogger(Application.class);
	public static void main(String[] args) throws Exception {
		Optional<String> version = Optional.ofNullable(Application.class.getPackage().getImplementationVersion());
		SpringApplication.run(Application.class, args);
		LOGGER.info(String.format("OrderProcess application %s started.", version.orElse("")));
	}
}
