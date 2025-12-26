package com.OTP_Login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void Sendotp(String to, Long otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Project Login OTP ");
        message.setText("Your Project Login OTP is "+otp+ " Valid for 2 minutes! Do not share with anyone.");
        mailSender.send(message);
    }
}
