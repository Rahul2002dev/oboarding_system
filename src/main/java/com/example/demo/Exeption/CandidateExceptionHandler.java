package com.example.demo.Exeption;


import com.example.demo.dto.responseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.List;
@Slf4j
@ControllerAdvice
public class CandidateExceptionHandler {
    private static final String message = "Exception while processing request";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<responseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("please enter a proper formate", ex);
        responseDto responsedto = new responseDto(message,"should be date in formate");
        return new ResponseEntity<>(responsedto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<responseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("NoArgumentNotValidExeption", ex);
        responseDto responsedto = new responseDto(message,"should be date in formate");
        return new ResponseEntity<>(responsedto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<responseDto> handleAuthenticationException(AuthenticationException ex) {
        log.error("Authenticaton Not working ! ", ex);
        responseDto responsedto = new responseDto(message,"should be date in formate");
        return new  ResponseEntity<>(responsedto, HttpStatus.UNAUTHORIZED );
    }
}
