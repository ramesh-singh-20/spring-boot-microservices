package com.alphacoder.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequest {
    private Long id;

    @NotNull(message = "Product name is required.")
    @NotEmpty(message = "Product name is required.")
    private String name;

    @NotNull(message = "Product description is required.")
    @NotEmpty(message = "Product description is required.")
    private String description;

    @NotNull(message = "Product price is required.")
    private BigDecimal price;
}
