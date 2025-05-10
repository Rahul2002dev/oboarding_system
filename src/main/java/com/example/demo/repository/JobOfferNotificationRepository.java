package com.example.demo.repository;

import com.example.demo.entity.JobOfferNotification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOfferNotificationRepository extends JpaRepository<JobOfferNotification, Long> {
}
