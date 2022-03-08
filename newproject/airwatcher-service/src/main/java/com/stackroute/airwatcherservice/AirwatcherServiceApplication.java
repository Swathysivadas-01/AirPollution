package com.stackroute.airwatcherservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient

@SpringBootApplication
public class AirwatcherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirwatcherServiceApplication.class, args);
	}

}
