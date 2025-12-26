package com.OTP_Login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OtpLoginApplication {

	public static void main(String[] args) {

		SpringApplication.run(OtpLoginApplication.class, args);
		System.out.println("OTP Based Project is Running");

	}


}
