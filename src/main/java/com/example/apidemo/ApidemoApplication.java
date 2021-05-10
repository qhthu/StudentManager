package com.example.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.example.apidemo"})
public class ApidemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApidemoApplication.class, args);
	}
	@Bean
	@Profile("dev")
	public String dev() {
		System.out.println("dev environment");
		return "dev";
	}
	@Bean
	@Profile("prod")
	public String prod() {
		System.out.println("prod environment");
		return "prod";
	}


}
