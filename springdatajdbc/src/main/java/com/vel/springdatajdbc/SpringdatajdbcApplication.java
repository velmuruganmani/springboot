package com.vel.springdatajdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringdatajdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajdbcApplication.class, args);
	}

}
