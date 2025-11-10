package com.github.satwiksanand.posSystem.service;

import com.github.satwiksanand.posSystem.payload.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto) throws Exception;
    InventoryDto updateInventory(Long id, InventoryDto inventoryDto) throws Exception;
    void deleteInventory(Long inventoryId) throws Exception;
    InventoryDto getInventoryById(Long inventoryId) throws Exception;
    InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId);
    List<InventoryDto> getAllInventoryByBranchId(Long branchId);
}
