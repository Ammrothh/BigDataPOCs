package com.viewmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ViewManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViewManagementServiceApplication.class, args);
	}

}
