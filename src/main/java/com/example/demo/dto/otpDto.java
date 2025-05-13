package com.example.demo.dto;

import lombok.Data;

@Data
public class otpDto {
    private String email;
    private String otp;
    private long expiryTime;
}
