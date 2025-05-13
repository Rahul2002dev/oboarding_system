package com.example.demo.service;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.JobOfferDto;
import com.example.demo.dto.JobOfferNotificationDTO;
import com.example.demo.entity.Candidate;
import com.example.demo.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendJobOfferNotification(JobOfferDto dto) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                dto
        );

    }

    public void sendotpEmail(String to,String otp){
        JobOfferDto dto = new JobOfferDto();
        dto.setCandidateEmail(to);
        dto.setCandidateName("User");
        dto.setPosition("otp for password reset is : "+otp);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                dto);
    }
}
