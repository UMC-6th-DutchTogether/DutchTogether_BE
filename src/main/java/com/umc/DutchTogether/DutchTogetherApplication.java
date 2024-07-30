package com.umc.DutchTogether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DutchTogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(DutchTogetherApplication.class, args);
	}

}
