package com.alphacoder.controller.advice;

import com.alphacoder.domain.response.ResponseDto;
import com.alphacoder.exception.ApplicationDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ProductControllerAdvice {

    @ExceptionHandler(value = ApplicationDomainException.class)
    public ResponseEntity<?> handleApplicationDomainException(ApplicationDomainException exception){
        log.error(exception.getMessage());
        ResponseDto responseDto= ResponseDto.forError(exception.getError());

        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
}
