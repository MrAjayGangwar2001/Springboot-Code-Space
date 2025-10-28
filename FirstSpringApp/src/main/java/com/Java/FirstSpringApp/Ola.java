package com.Java.FirstSpringApp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

// If We want to create a object in main class file of Another package class file 
// So We have two options-----------
// 1. By Constructor Injection
// 2. By Annotation   ===> Autowired => it create Aumatically constructor, Not need to Manually
// Let's See in mAin class


// @Component                            // it tell to main This class file is Exist for Configuration
	// Agar kisi bhi class se @Component Annotaion hta denge toh java  us class ko nhi Pahchan payega ye class ko run/configure nhi karega
@ConditionalOnProperty(name = "cab.book", havingValue = "Ola")
public class Ola implements Cab {
    public void Book(){
        String booking = "Your Ola has been Booked";
        System.out.println("Booking Status : "+booking);
    }
}
