package com.github.satwiksanand.posSystem.payload.dto;

import com.github.satwiksanand.posSystem.models.Store;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private Long storeId;
}
