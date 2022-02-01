package com.alphacoder.controller;

import com.alphacoder.domain.response.ResponseDto;
import com.alphacoder.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/products")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping(value= "/{id}")
    public ResponseEntity<ResponseDto> getProductById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.
                forSuccess(this.productService.getProductById(id)));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getProducts(@RequestParam(value="page", required = true)int page,
                                                   @RequestParam(value="pageSize", required = true)int pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.
                forSuccess(this.productService.getProducts(page, pageSize)));
    }
}
