package com.org.kodvix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class OnlineJobPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineJobPortalApplication.class, args);
		System.out.println("Hello");
	}

}
