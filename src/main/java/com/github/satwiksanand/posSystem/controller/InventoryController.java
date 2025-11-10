package com.github.satwiksanand.posSystem.controller;

import com.github.satwiksanand.posSystem.payload.dto.InventoryDto;
import com.github.satwiksanand.posSystem.payload.response.ApiResponse;
import com.github.satwiksanand.posSystem.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) throws Exception {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> updateInventory(@PathVariable Long id, @RequestBody InventoryDto inventoryDto) throws Exception{
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInventory(@PathVariable Long id) throws Exception{
        inventoryService.deleteInventory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("inventory deleted successfully!");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDto> getInventoryByBranchIdAndProductId(@PathVariable Long branchId, @PathVariable Long productId){
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId, branchId));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getInventoryByBranch(@PathVariable Long branchId){
        return ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long inventoryId) throws Exception {
        return ResponseEntity.ok(inventoryService.getInventoryById(inventoryId));
    }
}
