package com.alphacoder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@RefreshScope
public class TestController {

    @Value("${app.test}")
    private String test;

    @GetMapping
    public String test(){
        return test;
    }
}
