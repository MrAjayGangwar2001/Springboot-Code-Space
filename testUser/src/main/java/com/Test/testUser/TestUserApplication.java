package com.Test.testUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestUserApplication.class, args);
		System.out.println("This is Self Test CRUD Operation for User.");
	}

}
