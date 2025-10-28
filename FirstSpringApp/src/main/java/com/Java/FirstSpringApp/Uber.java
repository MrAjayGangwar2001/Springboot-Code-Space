package com.Java.FirstSpringApp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

// @ConditionOnProperty Annotation ye btata hai ki kis condition par konsa method run hoga
// @Component
@ConditionalOnProperty(name = "cab.book", havingValue = "Uber")
public class Uber implements Cab {
    public void Book() {
        String booking = "Uber cab booked";
        System.out.println("Booking Status : " + booking);
    }
}
