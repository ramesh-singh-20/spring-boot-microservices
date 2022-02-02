package com.alphacoder.controller.advice;

import com.alphacoder.domain.error.ErrorDto;
import com.alphacoder.domain.response.ResponseDto;
import com.alphacoder.exception.ApplicationDomainException;
import com.alphacoder.util.ProductUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        log.error(ex.getMessage());
        BindingResult bindingResult= ex.getBindingResult();
        List<FieldError> fieldErrors= bindingResult.getFieldErrors();
        List<ErrorDto> errors= new ArrayList<>();
        fieldErrors.stream().forEach(error -> {
            log.error(error.getDefaultMessage());
            ErrorDto errorDto= ProductUtil.createErrorDto("Product_1005", error.getDefaultMessage());
            errors.add(errorDto);
        });

        ResponseDto responseDto= ResponseDto.forError(errors);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException ex){
        log.error(ex.getLocalizedMessage());
        ErrorDto error= ProductUtil.createErrorDto("PRODUCT_1006",
                "Something went wrong while connecting to database.");
        ResponseDto responseDto= ResponseDto.forError(error);
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex){
        log.error(ex.getLocalizedMessage());
        ErrorDto error= ProductUtil.createErrorDto("PRODUCT_1007",
                "No data exists for the given input. Please check again");
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
