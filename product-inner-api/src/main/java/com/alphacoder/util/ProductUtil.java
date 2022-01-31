package com.alphacoder.util;

import com.alphacoder.domain.error.ErrorDto;

public class ProductUtil {

    private ProductUtil(){

    }

    public static ErrorDto createErrorDto(String code, String message){
        ErrorDto error= new ErrorDto(code, message);
        return error;
    }
}

