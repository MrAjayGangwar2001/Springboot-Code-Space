package com.OTP_Login.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private Long otp;
    private Instant expiryTime;
    private LocalDateTime createdAt;
}
