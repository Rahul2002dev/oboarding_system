package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.enums.OnboardingStatus;

import com.example.demo.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate")
public class Candidate {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;

   @Enumerated(EnumType.STRING)
   private Status status;

   @Enumerated(EnumType.STRING)
   private OnboardingStatus onboardingStatus;

   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;

   @OneToOne(mappedBy = "candidate" , cascade = CascadeType.ALL)
   private CandidatePersonalInfo candidatePersonalInfo;

   @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
   private CandidateBankInfo candidateBankInfo;

   @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
   private CandidateEducation candidateEducation;

   @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
   private List<CandidateDocument> candidateDocument;

   @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
   private List<JobOfferNotification> jobOfferNotifications;

   @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
   private List<FileEntity> fileEntities;
}
;