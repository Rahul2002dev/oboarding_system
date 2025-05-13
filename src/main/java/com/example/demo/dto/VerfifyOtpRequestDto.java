package com.example.demo.dto;


import lombok.Data;

@Data
public class VerfifyOtpRequestDto {
    private String email;
    private String otp;
}
