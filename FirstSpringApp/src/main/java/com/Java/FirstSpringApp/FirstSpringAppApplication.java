package com.Java.FirstSpringApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

// This is Normal/Simple Method
// public class FirstSpringAppApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(FirstSpringAppApplication.class, args);
// 		System.out.println("Hello Spring App");

// 		Ola ola = new Ola();
// 		ola.Book();
// 	}

// }

// Implementing commandLine Runner
// public class FirstSpringAppApplication implements CommandLineRunner {
public class FirstSpringAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringAppApplication.class, args);
		System.out.println("Hello Spring App");

	}
	//  Ola obj = new Ola();

	// @Autowired
	// private Uber ubj;
	// // public void FirstSpringAppApplication(Uber ubj){
	// // 	this.ubj = ubj;
	// // }
	
	// // By Annotation
	// // @Autowired
	// //private Ola obj;
	// // By Constructor Injection
	// // public FirstSpringAppApplication(Ola obj){         // Constructor not required if we use @Autowired Annotation
	// // 	this.obj = obj;
	// // }
	// @Override
	// public void run(String... args) throws Exception {         // This Method Will Run before Main Method
	// 	//obj.Book();
	// 	ubj.Book();
	
	// }


	// Agar kisi bhi class se @Component Annotaion hta denge toh java  us class ko nhi Pahchan payega ye class ko run/configure nhi karega

	// private Cab cab;

	// public FirstSpringAppApplication(Cab cab){
	// 	this.cab = cab;
	// }

	// @Override
	// public void run(String... args) throws Exception {
	// 	cab.Book();
	// }

}
