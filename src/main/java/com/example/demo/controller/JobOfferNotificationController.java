package com.example.demo.controller;

import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-offer-notification")
public class JobOfferNotificationController {
    @Autowired
    EmailService emailService;

    @PostMapping("/{id}")
    public ResponseEntity<?> sendNotification(@PathVariable Long id){
        Boolean status = emailService.sendJobOfferEmail(id);
        if(status){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(500).body("Failed to send job offer email");
        }
    }
}
