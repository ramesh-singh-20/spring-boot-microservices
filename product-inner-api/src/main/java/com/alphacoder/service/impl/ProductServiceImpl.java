package com.alphacoder.service.impl;

import com.alphacoder.domain.error.ErrorDto;
import com.alphacoder.domain.request.ProductRequest;
import com.alphacoder.entity.Product;
import com.alphacoder.exception.ApplicationDomainException;
import com.alphacoder.repository.ProductRepository;
import com.alphacoder.service.ProductService;
import com.alphacoder.util.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public Product getProductById(String id) {
        log.info("Product id: "+id);

        Optional<Product> productOptional= this.repository.findById(id);

        if(productOptional.isPresent()){
            return productOptional.get();
        }else{
            log.error("Product not found in database.");
            ErrorDto errorDto= ProductUtil.
                    createErrorDto("PRODUCT_1001", "Product not found with id: "+ id);
            throw new ApplicationDomainException(Arrays.asList(errorDto), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Product> getProducts(int page, int pageSize) {
        return null;
    }

    @Override
    public void addProduct(ProductRequest request) {

    }

    @Override
    public void updateProduct(ProductRequest request) {

    }

    @Override
    public void deleteProduct(String id) {

    }
}
