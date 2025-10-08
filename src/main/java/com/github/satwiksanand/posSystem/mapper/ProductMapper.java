package com.github.satwiksanand.posSystem.mapper;

import com.github.satwiksanand.posSystem.models.Product;
import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.payload.dto.ProductDto;

public class ProductMapper {
    public static ProductDto toDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .storeId(product.getStore() != null ? product.getStore().getId() : null)
                .imageUrl(product.getImageUrl())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static Product toEntity(ProductDto productDto, Store store){
        return Product.builder()
                .mrp(productDto.getMrp())
                .sellingPrice(productDto.getSellingPrice())
                .imageUrl(productDto.getImageUrl())
                .name(productDto.getName())
                .brand(productDto.getBrand())
                .description(productDto.getDescription())
                .sku(productDto.getSku())
                .store(store)
                .build();
    }
}
