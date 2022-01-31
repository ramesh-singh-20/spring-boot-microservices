package com.alphacoder.service.impl;

import com.alphacoder.domain.request.ProductRequest;
import com.alphacoder.entity.Product;
import com.alphacoder.repository.ProductRepository;
import com.alphacoder.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public Product getProductById(String id) {
        Optional<Product> productOptional= this.repository.findById(id);

        if(productOptional.isPresent()){
            return productOptional.get();
        }else{

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
