package com.example.demo.controller;

import com.example.demo.repository.FileRepository;
import com.example.demo.service.FIleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("api/files")
public class FileController {

    @Autowired
    private FIleService fileService;


    @PutMapping("/upload/{candidateId}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable Long candidateId){
        try{
            fileService.storeFile(file,candidateId);
            return ResponseEntity.ok("file uploaded successfully");

        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
