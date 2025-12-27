package com.OTP_Login.OTP;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OTPUtility {
    public static Long GenerateOTP(){
        return Long.valueOf(100000 + new Random().nextInt(900000));
    }
}
