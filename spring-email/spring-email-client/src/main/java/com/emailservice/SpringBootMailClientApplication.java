package com.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.emailservice"})
@SpringBootApplication
public class SpringBootMailClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMailClientApplication.class, args);
	}

}
