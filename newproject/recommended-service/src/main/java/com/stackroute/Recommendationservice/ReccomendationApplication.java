package com.stackroute.Recommendationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ReccomendationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReccomendationApplication.class, args);
	}

}
