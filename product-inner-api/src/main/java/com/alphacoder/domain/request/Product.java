package com.alphacoder.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
}
