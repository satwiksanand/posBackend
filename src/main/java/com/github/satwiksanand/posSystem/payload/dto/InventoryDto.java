package com.github.satwiksanand.posSystem.payload.dto;

import com.github.satwiksanand.posSystem.models.Branch;
import com.github.satwiksanand.posSystem.models.Product;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class InventoryDto {
    private Long id;
    private BranchDto branch;
    private Long branchId;
    private Long productId;
    private ProductDto product;
    private Integer quantity;
    private LocalTime lastUpdate;
}
