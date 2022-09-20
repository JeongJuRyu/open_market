package com.tmax.cm.superstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SuperstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperstoreApplication.class, args);
	}
}
