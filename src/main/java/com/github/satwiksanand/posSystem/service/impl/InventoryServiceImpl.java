package com.github.satwiksanand.posSystem.service.impl;

import com.github.satwiksanand.posSystem.mapper.InventoryMapper;
import com.github.satwiksanand.posSystem.models.Branch;
import com.github.satwiksanand.posSystem.models.Inventory;
import com.github.satwiksanand.posSystem.models.Product;
import com.github.satwiksanand.posSystem.payload.dto.InventoryDto;
import com.github.satwiksanand.posSystem.repository.BranchRepository;
import com.github.satwiksanand.posSystem.repository.InventoryRepository;
import com.github.satwiksanand.posSystem.repository.ProductRepository;
import com.github.satwiksanand.posSystem.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception {
        Branch branch = branchRepository.findById(inventoryDto.getBranchId()).orElseThrow(
                () -> new Exception("branch not found!")
        );
        Product product = productRepository.findById(inventoryDto.getProductId()).orElseThrow(
                () -> new Exception("product not found!")
        );
        Inventory inventory = InventoryMapper.toEntity(inventoryDto, branch, product);
        return InventoryMapper.toDto(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDto updateInventory(Long id, InventoryDto inventoryDto) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                () -> new Exception("inventory does not exists")
        );
        inventory.setLastUpdate(LocalTime.now());
        inventory.setQuantity(inventoryDto.getQuantity());
        return InventoryMapper.toDto(inventoryRepository.save(inventory));
    }

    @Override
    public void deleteInventory(Long inventoryId) throws Exception {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(
                () -> new Exception("inventory does not exists")
        );
        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDto getInventoryById(Long inventoryId) throws Exception {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(
                () -> new Exception("inventory does not exists")
        );
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventoryByBranchId(Long branchId) {
        List<Inventory> inventories = inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(InventoryMapper::toDto).toList();
    }
}
