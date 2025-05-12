package com.example.demo.service;

import com.example.demo.entity.Candidate;
import com.example.demo.entity.FileEntity;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FIleService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public FileEntity storeFile(MultipartFile file ,Long candidateId) throws IOException {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFiletype(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setCandidate(candidate); // ❗ Important line

        return fileRepository.save(fileEntity);
    }
}
