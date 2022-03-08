package com.stackroute.zuulapiGateway;

import com.stackroute.zuulapiGateway.Filter.ErrorFilter;
import com.stackroute.zuulapiGateway.Filter.PostFilter;
import com.stackroute.zuulapiGateway.Filter.PreFilter;
import com.stackroute.zuulapiGateway.Filter.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulapiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulapiGatewayApplication.class, args);
	}

	@Bean
	public PreFilter preFilter(){
		return  new PreFilter();
	}
	@Bean
	public PostFilter postFilter(){
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter(){
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter(){
		return  new RouteFilter();
	}
}
