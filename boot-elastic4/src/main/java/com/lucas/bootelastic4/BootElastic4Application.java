package com.lucas.bootelastic4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = "com.lucas.bootelastic4.modules.repository.jpa")
@SpringBootApplication
public class BootElastic4Application {

	public static void main(String[] args) {
		SpringApplication.run(BootElastic4Application.class, args);
	}

}
