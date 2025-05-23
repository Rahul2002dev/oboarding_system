package com.example.demo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePersonalInfoDto {
    @NotNull(message = "dob must be required in this field")
    private Date dob;
    @NotEmpty
    private String gender;
    @NotEmpty
    private String address;
    @NotEmpty
    private String nationality;
}
