package com.github.satwiksanand.posSystem.payload.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ProductDto {
    private Long id;
    private String name;
    private String sku;
    private String description;
    private Double mrp;
    private Double sellingPrice;
    private String brand;
    private String imageUrl;
//    private Category category;
    private Long storeId;
    private Long categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
