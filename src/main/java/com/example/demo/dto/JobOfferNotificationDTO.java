package com.example.demo.dto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobOfferNotificationDTO {
    private Boolean sent;
    private LocalDateTime sentAt;
    private Integer retryCount;
    private String errorMessage;
}

