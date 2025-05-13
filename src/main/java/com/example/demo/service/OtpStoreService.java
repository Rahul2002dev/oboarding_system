package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpStoreService {
    private final ConcurrentHashMap<String,String> otpMap = new ConcurrentHashMap<>();
    
    public void storeotp(String name,String otp){
       otpMap.put(name,otp);
    }
    public boolean verifyOtp(String email,String otp){
        return otpMap.containsKey(email) && otpMap.get(email).equals(otp);
    }

    public void clearOtp(String email){
        otpMap.remove(email);
    }
}
