package com.alphacoder.domain.response;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDto {
    private List<ProductResponse> products;
    private int totalPages;
    private Long totalRecords;
    private int pageSize;
}
