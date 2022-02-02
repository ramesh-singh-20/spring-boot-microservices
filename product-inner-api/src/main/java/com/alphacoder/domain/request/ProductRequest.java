package com.alphacoder.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequest {
    private Long id;

    @NotNull(message = "Product name cannot be null.")
    @NotEmpty(message = "Product name cannot be empty.")
    private String name;

    @NotNull(message = "Product description cannot be null.")
    @NotEmpty(message = "Product description cannot be empty.")
    private String description;

    @NotNull(message = "Product price cannot be null.")
    @Positive(message = "Product price must be greater than 0.")
    private BigDecimal price;
}
