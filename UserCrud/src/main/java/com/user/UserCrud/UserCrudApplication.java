package com.user.UserCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCrudApplication.class, args);
		System.out.println("This is Basic USER CRUD(Create, Read, Update, Delete) Operation and Project Has Started.");
	}

}
