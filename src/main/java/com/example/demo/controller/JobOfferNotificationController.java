package com.example.demo.controller;

import com.example.demo.dto.JobOfferDto;
import com.example.demo.entity.JobOfferNotification;
import com.example.demo.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/job-offer-notification")
public class JobOfferNotificationController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/notify")
    public ResponseEntity<String> notifyCandidate(@RequestBody @Valid JobOfferDto dto) {
        emailService.sendJobOfferNotification(dto);
        return ResponseEntity.ok("Job offer notification sent via RabbitMQ.");
    }


}
