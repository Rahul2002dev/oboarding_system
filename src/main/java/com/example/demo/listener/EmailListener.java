package com.example.demo.listener;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.JobOfferDto;
import com.example.demo.dto.JobOfferNotificationDTO;
import com.example.demo.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailListener {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void listen(JobOfferDto  jobOfferDto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(jobOfferDto.getCandidateEmail());
            helper.setFrom(fromEmail);
            helper.setSubject("Job Offer Notification");

            String body = "<h3>Congratulations " + jobOfferDto.getCandidateName() + "!</h3>" +
                    "<p>You have been selected for the position of <b>" + jobOfferDto.getPosition() + "</b>.</p>";

            helper.setText(body, true);
            mailSender.send(message);
            System.out.println("Email sent to: " + jobOfferDto.getCandidateEmail());
        } catch (Exception e) {
            System.err.println("Email sending failed: " + e.getMessage());
        }
    }
}
