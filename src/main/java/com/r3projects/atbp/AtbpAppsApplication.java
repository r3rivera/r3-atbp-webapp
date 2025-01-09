package com.r3projects.atbp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
public class AtbpAppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtbpAppsApplication.class, args);
	}

}
