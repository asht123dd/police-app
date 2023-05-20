package com.ashu.police;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
public class PoliceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoliceAppApplication.class, args);
	}

}
