package com.workintech.s17d2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.workintech.s17d2", "tax"})
public class S17d2Application {
	public static void main(String[] args) {
		SpringApplication.run(S17d2Application.class, args);
	}
}
