package com.github.satwiksanand.posSystem.mapper;

import com.github.satwiksanand.posSystem.models.Category;
import com.github.satwiksanand.posSystem.payload.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toDto(Category category){
        return CategoryDto.builder()
                .name(category.getName())
                .storeId(category.getStore() != null ? category.getStore().getId() : null)
                .build();
    }
}
