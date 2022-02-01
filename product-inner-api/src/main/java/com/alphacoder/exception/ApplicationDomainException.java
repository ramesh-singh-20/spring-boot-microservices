package com.alphacoder.exception;

import com.alphacoder.domain.error.ErrorDto;
import org.springframework.http.HttpStatus;

public class ApplicationDomainException extends RuntimeException{
    private final ErrorDto error;
    private final HttpStatus status;

    public ApplicationDomainException(ErrorDto error, HttpStatus status){
        super();
        this.error= error;
        this.status= status;
    }

    public ErrorDto getError() {
        return error;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
