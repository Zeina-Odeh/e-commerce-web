package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stockQuantity;
    private String imageUrl;
    private Long categoryId;
}
