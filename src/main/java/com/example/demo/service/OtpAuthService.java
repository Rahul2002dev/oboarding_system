package com.example.demo.service;

import com.example.demo.dto.ForgotPasswordRequest;
import com.example.demo.dto.ResetPasswordRequest;
import com.example.demo.dto.VerfifyOtpRequestDto;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpAuthService {
    private final UserDetailsRepository  userDetailsRepository;
    private final EmailService emailService;
    private final OtpUtil otpUtil;
    private final OtpStoreService otpStoreService;
    private final PasswordEncoder passwordEncoder;

    public String sendOtp(ForgotPasswordRequest request) {
        Users user = userDetailsRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String otp = otpUtil.generateOtp();
        otpStoreService.storeotp(user.getEmail(), otp);
        emailService.sendotpEmail(user.getEmail(), otp);
        return "OTP sent to email.";
    }

    public String verifyOtp(VerfifyOtpRequestDto request) {
        boolean isValid = otpStoreService.verifyOtp(request.getEmail(), request.getOtp());
        if (!isValid) {
            throw new RuntimeException("Invalid OTP");
        }
        return "OTP verified. You may now reset your password.";
    }

    public String resetPassword(ResetPasswordRequest request) {
        Users user = userDetailsRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userDetailsRepository.save(user);
        otpStoreService.clearOtp(user.getEmail());
        return "Password reset successful.";
    }
}
