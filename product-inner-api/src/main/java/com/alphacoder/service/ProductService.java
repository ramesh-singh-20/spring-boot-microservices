package com.alphacoder.service;

import com.alphacoder.domain.request.ProductRequest;
import com.alphacoder.domain.response.ProductResponse;
import com.alphacoder.entity.Product;

import java.util.List;

public interface ProductService {
    public ProductResponse getProductById(Long id);
    public List<ProductResponse> getProducts(int page, int pageSize);
    public void addProduct(ProductRequest request);
    public void updateProduct(ProductRequest request);
    public void deleteProduct(String id);
}
