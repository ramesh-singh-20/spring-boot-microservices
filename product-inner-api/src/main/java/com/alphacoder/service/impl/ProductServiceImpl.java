package com.alphacoder.service.impl;

import com.alphacoder.domain.error.ErrorDto;
import com.alphacoder.domain.request.ProductRequest;
import com.alphacoder.domain.response.ProductResponse;
import com.alphacoder.domain.response.ProductResponseDto;
import com.alphacoder.entity.Product;
import com.alphacoder.exception.ApplicationDomainException;
import com.alphacoder.mapper.ProductMapper;
import com.alphacoder.repository.ProductRepository;
import com.alphacoder.service.ProductService;
import com.alphacoder.util.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ProductResponse getProductById(Long id) {
        log.info("Product id: "+id);

        Optional<Product> productOptional= this.repository.findById(id);

        if(productOptional.isPresent()){
            return this.mapper.mapProductEntityToProductResponse(productOptional.get());
        }else{
            log.error("Product not found in database.");
            ErrorDto errorDto= ProductUtil.
                    createErrorDto("PRODUCT_1001", "Product not found with id: "+ id);
            throw new ApplicationDomainException(errorDto, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ProductResponseDto getProducts(int page, int pageSize) {
        Page<Product> paginatedProducts= getPaginatedProducts(page, pageSize);
        ProductResponseDto productResponseDto= new ProductResponseDto();
        List<ProductResponse> products;

        if(paginatedProducts.hasContent()){
            products= new ArrayList<>();
            paginatedProducts.stream().forEach(
                    product -> products.add(this.mapper.mapProductEntityToProductResponse(product)));

            if(!products.isEmpty()){
                productResponseDto.setProducts(products);
                productResponseDto.setTotalRecords(paginatedProducts.getTotalElements());
                productResponseDto.setTotalPages(paginatedProducts.getTotalPages());
                productResponseDto.setPageSize(pageSize);
            }

        }else{
            log.error("Products not found in database.");
            ErrorDto errorDto= ProductUtil.
                    createErrorDto("PRODUCT_1002", "No products found in the database.");
            throw new ApplicationDomainException(errorDto, HttpStatus.NOT_FOUND);
        }

        return productResponseDto;
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

    private Page<Product> getPaginatedProducts(int page, int pageSize){
        Pageable pageable= PageRequest.of(page, pageSize);
        return repository.findAll(pageable);
    }
}
