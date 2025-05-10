package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CandidatePersonalInfoRepository candidatePersonalInfoRepository;

    @Autowired
    CandidateEducationRepository candidateEducationRepository;

    @Autowired
    CandidateBankInfoRepository candidateBankInfoRepository;

    @Autowired
    NotificationRepository  notificationRepository;

    @Autowired
    CandidateDocumentRepository candidateDocumentRepository;

    public void addNewCandidate(CandidateDto candidateDto) {
        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(candidateDto, candidate);
        candidate.setCreatedAt(LocalDateTime.now());
        candidate.setUpdatedAt(LocalDateTime.now());
        candidateRepository.save(candidate);
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElseThrow();
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public void addNewCandidatePersonalInfo(Long candidateId,CandidatePersonalInfoDto candidatePersonalInfoDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));
        CandidatePersonalInfo  candidatePersonalInfo = new CandidatePersonalInfo();
        candidatePersonalInfo.setDob(candidatePersonalInfoDto.getDob());
        candidatePersonalInfo.setAddress(candidatePersonalInfoDto.getAddress());
        candidatePersonalInfo.setGender(candidatePersonalInfoDto.getGender());
        candidatePersonalInfo.setNationality(candidatePersonalInfoDto.getNationality());
        candidatePersonalInfo.setCandidate(candidate);
        candidatePersonalInfoRepository.save(candidatePersonalInfo);
    }

    public void addBankInfo(Long candidateId, CandidateBankInfoDto  candidateBankInfoDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));
        CandidateBankInfo candidateBankInfo = new CandidateBankInfo();
        candidateBankInfo.setBankName(candidateBankInfoDto.getBankName());
        candidateBankInfo.setAccountNumber(String.valueOf(candidateBankInfoDto.getAccountNumber()));
        candidateBankInfo.setIfscCode(candidateBankInfoDto.getIfscCode());
        candidateBankInfo.setCandidate(candidate); // Set the candidate reference
        candidateBankInfoRepository.save(candidateBankInfo);
    }

    public void addEducation(Long candidateId,CandidateEducationDto  candidateEducationDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));
        CandidateEducation candidateEducation = new CandidateEducation();
        candidateEducation.setYearOfGraduation(String.valueOf(candidateEducationDto.getYearOfGraduation()));
        candidateEducation.setDegree(candidateEducationDto.getDegree());
        candidateEducation.setInstitution(candidateEducationDto.getInstitution());
        candidateEducation.setCandidate(candidate); // Set the candidate reference
        candidateEducationRepository.save(candidateEducation);
    }

    public void AddDocument(Long candidateId,CandiateDocumentDto  candiateDocumentDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));
        CandidateDocument candidateDocument = new CandidateDocument();
        candidateDocument.setDocumentUrl(candiateDocumentDto.getDocumentUrl());
        candidateDocument.setDocumentType(candiateDocumentDto.getDocumentType());
        candidateDocument.setIsVerified(candiateDocumentDto.getIsVerified());
        candidateDocument.setCandidate(candidate);
        candidateDocumentRepository.save(candidateDocument);
    }

    public void AddNotification(Long candidateId,JobOfferNotificationDTO  jobOfferNotificationDTO) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));
        JobOfferNotification jobOfferNotification = new JobOfferNotification();
        jobOfferNotification.setSent(jobOfferNotificationDTO.getSent());
        jobOfferNotification.setRetryCount(jobOfferNotificationDTO.getRetryCount());
        jobOfferNotification.setErrorMessage(jobOfferNotificationDTO.getErrorMessage());
        jobOfferNotification.setSentAt(jobOfferNotificationDTO.getSentAt());
        jobOfferNotification.setCandidate(candidate);
        notificationRepository.save(jobOfferNotification);

    }
}
