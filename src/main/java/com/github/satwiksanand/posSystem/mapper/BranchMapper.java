package com.github.satwiksanand.posSystem.mapper;

import com.github.satwiksanand.posSystem.models.Branch;
import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.payload.dto.BranchDto;

public class BranchMapper {
    public static BranchDto toDto(Branch branch){
        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .email(branch.getEmail())
                .phone(branch.getPhone())
                .StoreId(branch.getStore() != null ? branch.getStore().getId() : null)
                .store(StoreMapper.toDTO(branch.getStore()))
                .manager(branch.getManager() != null ? UserMapper.toDTO(branch.getManager()) : null)
                .closeTime(branch.getCloseTime())
                .openTime(branch.getOpenTime())
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .workingDays(branch.getWorkingDays())
                .build();
    }

    public static Branch toEntity(BranchDto branchDto, Store store){
        return Branch.builder()
                .id(branchDto.getId())
                .name(branchDto.getName())
                .email(branchDto.getEmail())
                .store(store)
                .address(branchDto.getAddress())
                .openTime(branchDto.getOpenTime())
                .closeTime(branchDto.getCloseTime())
                .createdAt(branchDto.getCreatedAt())
                .updatedAt(branchDto.getUpdatedAt())
                .phone(branchDto.getPhone())
                .workingDays(branchDto.getWorkingDays())
                .build();
    }
}
