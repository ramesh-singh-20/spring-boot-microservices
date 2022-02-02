package com.alphacoder.controller;

import com.alphacoder.domain.request.ProductRequest;
import com.alphacoder.domain.response.ResponseDto;
import com.alphacoder.domain.response.ResponseStatus;
import com.alphacoder.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> addProduct(@Valid @RequestBody ProductRequest request){
        this.productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ResponseStatus.SUCCESS, null));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> updateProduct(@Valid @RequestBody ProductRequest request){
        this.productService.updateProduct(request);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ResponseStatus.SUCCESS, null));
    }


}
