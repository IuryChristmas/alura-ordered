package br.com.alurafood.ordered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class OrderedApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderedApplication.class, args);
	}

}
