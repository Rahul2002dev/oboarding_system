package com.example.demo.util;


import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {

    public String generateOtp() {
        return String.format("%06d",new Random().nextInt(10000));
    }
}
