package com.github.satwiksanand.posSystem.service;

import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.BranchDto;

import java.util.List;

public interface BranchService {
    BranchDto createBranch(BranchDto branchDto) throws UserException;
    BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception;
    void deleteBranch(Long id) throws Exception;
    List<BranchDto> getAllBranchesByStoreId(Long storeId);
    BranchDto getBranchById(Long id) throws Exception;
}
