package com.alphacoder.controller.advice;

import com.alphacoder.domain.error.ErrorDto;
import com.alphacoder.domain.response.ResponseDto;
import com.alphacoder.exception.ApplicationDomainException;
import com.alphacoder.util.ProductUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException
            (MissingServletRequestParameterException exception){
        log.error(exception.getLocalizedMessage());
        ErrorDto error= ProductUtil.createErrorDto("PRODUCT_1003", exception.getMessage());
        ResponseDto responseDto= ResponseDto.forError(error);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        log.error(ex.getLocalizedMessage());
        ErrorDto error= ProductUtil.createErrorDto("PRODUCT_1004",
                "Something went wrong. Please try again after sometime.");
        ResponseDto responseDto= ResponseDto.forError(error);
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
