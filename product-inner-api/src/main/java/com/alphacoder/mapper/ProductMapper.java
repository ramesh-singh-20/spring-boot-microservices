package com.alphacoder.mapper;

import com.alphacoder.domain.request.ProductRequest;
import com.alphacoder.domain.response.ProductResponse;
import com.alphacoder.entity.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    public Product mapProductRequestToProductEntity(ProductRequest request);
    public ProductResponse mapProductEntityToProductResponse(Product product);
}
