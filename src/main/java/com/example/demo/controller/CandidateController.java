package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Candidate;
import com.example.demo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping()
    public ResponseEntity<?> saveNewCandidate(@RequestBody @Validated CandidateDto candidateDto) {
        candidateService.addNewCandidate(candidateDto);
        return ResponseEntity.ok(new MessageResponse("Candidate added successfully!"));
    }

    @GetMapping("/allinfo")
    public ResponseEntity<List<Candidate>> getAll(){
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidatesById(@PathVariable Long id){
        return ResponseEntity.ok(candidateService.getCandidateById(id));
    }

    @PutMapping("/{id}/personal-info")
    public ResponseEntity<?> saveCandidatePersonalInfo(@PathVariable Long id,@RequestBody @Validated CandidatePersonalInfoDto candidatePersonalInfoDto){
        System.out.println("Put request received");
        candidateService.addNewCandidatePersonalInfo(id,candidatePersonalInfoDto);
        return ResponseEntity.ok(new MessageResponse("Candidate personal info added successfully!"));
    }

    @PutMapping("/{id}/bank-info")
    public ResponseEntity<?> addBankInfo(@PathVariable Long id,@RequestBody @Validated  CandidateBankInfoDto dto) {
        candidateService.addBankInfo(id,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bank info added successfully");
    }

    // Add education info
    @PutMapping("/{id}/education")
    public ResponseEntity<?> addEducation(@PathVariable Long id,@RequestBody CandidateEducationDto dto) {
        candidateService.addEducation(id,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Education info added successfully");
    }

    // Add document
    @PutMapping("/{id}/document")
    public ResponseEntity<?> addDocument(@PathVariable Long id,@RequestBody CandiateDocumentDto dto) {
        candidateService.AddDocument(id,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Document added successfully");
    }

    // Add job offer notification
    @PutMapping("/{id}/notification")
    public ResponseEntity<?> addNotification(@PathVariable Long id,@RequestBody JobOfferNotificationDTO dto) {
        candidateService.AddNotification(id,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job offer notification added successfully");
    }
}
