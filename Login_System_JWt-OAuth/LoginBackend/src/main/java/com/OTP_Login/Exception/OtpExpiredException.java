package com.OTP_Login.Exception;

public class OtpExpiredException extends RuntimeException {
    public OtpExpiredException(String msg) {
        super(msg);
    }
}
