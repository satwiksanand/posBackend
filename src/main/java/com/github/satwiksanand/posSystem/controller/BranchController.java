package com.github.satwiksanand.posSystem.controller;

import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.payload.dto.BranchDto;
import com.github.satwiksanand.posSystem.payload.response.ApiResponse;
import com.github.satwiksanand.posSystem.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) throws UserException {
        return ResponseEntity.ok(branchService.createBranch(branchDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDto>> getAllBranchesByStoreId(@PathVariable Long storeId) throws Exception {
        return ResponseEntity.ok(branchService.getAllBranchesByStoreId(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable Long id, @RequestBody BranchDto branchDto) throws Exception {
        return ResponseEntity.ok(branchService.updateBranch(id, branchDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("branch deleted successfully!");
        return ResponseEntity.ok(apiResponse);
    }
}
