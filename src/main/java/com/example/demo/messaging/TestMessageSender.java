package com.example.demo.messaging;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.JobOfferDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestMessageSender implements CommandLineRunner {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) {
        JobOfferDto dto = new JobOfferDto();
        dto.setCandidateEmail("rahul26patel2002@gmail.com");
        dto.setCandidateName("RahulPate");
        dto.setPosition("Java-Backend Engineer");

        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, dto);
            System.out.println("Test message sent to RabbitMQ for: " + dto.getCandidateEmail());
        } catch (Exception e) {
            System.err.println("Failed to send test message: " + e.getMessage());
        }
    }
}
