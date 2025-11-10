package com.github.satwiksanand.posSystem.mapper;

import com.github.satwiksanand.posSystem.models.Branch;
import com.github.satwiksanand.posSystem.models.Inventory;
import com.github.satwiksanand.posSystem.models.Product;
import com.github.satwiksanand.posSystem.payload.dto.InventoryDto;

public class InventoryMapper {
    public static InventoryDto toDto(Inventory inventory){
        return InventoryDto.builder()
                .id(inventory.getId())
                .quantity(inventory.getQuantity())
                .lastUpdate(inventory.getLastUpdate())
                .branch(inventory.getBranch() != null ? BranchMapper.toDto(inventory.getBranch()) : null)
                .product(inventory.getProduct() != null ? ProductMapper.toDto(inventory.getProduct()) : null)
                .branchId(inventory.getBranch() != null ? inventory.getBranch().getId() : null)
                .productId(inventory.getProduct() != null ? inventory.getProduct().getId() : null)
                .build();
    }

    public static Inventory toEntity(InventoryDto inventoryDto, Branch branch, Product product){
        return Inventory.builder()
                .product(product)
                .branch(branch)
                .quantity(inventoryDto.getQuantity())
                .lastUpdate(inventoryDto.getLastUpdate())
                .id(inventoryDto.getId())
                .build();
    }
}
