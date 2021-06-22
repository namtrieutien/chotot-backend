package com.sunny.Sunny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SunnyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunnyApplication.class, args);
	}

}
