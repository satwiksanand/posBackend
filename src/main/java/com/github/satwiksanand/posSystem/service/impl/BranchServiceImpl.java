package com.github.satwiksanand.posSystem.service.impl;

import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.mapper.BranchMapper;
import com.github.satwiksanand.posSystem.models.Branch;
import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.BranchDto;
import com.github.satwiksanand.posSystem.repository.BranchRepository;
import com.github.satwiksanand.posSystem.repository.StoreRepository;
import com.github.satwiksanand.posSystem.service.BranchService;
import com.github.satwiksanand.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;


    @Override
    public BranchDto createBranch(BranchDto branchDto) throws UserException {
        User currentUser = userService.getCurrentUser();
        System.out.println("hit");
        Store store = storeRepository.findByStoreAdminId(currentUser.getId());
        System.out.println("hit");
        Branch branch = BranchMapper.toEntity(branchDto, store);
        System.out.println("hit");
        Branch savedBranch = branchRepository.save(branch);
        return BranchMapper.toDto(savedBranch);
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                () -> new Exception("branch not found!")
        );
        existing.setName(branchDto.getName());
        existing.setEmail(branchDto.getEmail());
        existing.setUpdatedAt(branchDto.getUpdatedAt());
        existing.setPhone(branchDto.getPhone());
        existing.setAddress(branchDto.getAddress());
        existing.setOpenTime(branchDto.getOpenTime());
        existing.setCloseTime(branchDto.getCloseTime());
        existing.setWorkingDays(branchDto.getWorkingDays());

        return BranchMapper.toDto(branchRepository.save(existing));
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                () -> new Exception("branch not found!")
        );
        branchRepository.delete(existing);
    }

    @Override
    public List<BranchDto> getAllBranchesByStoreId(Long storeId) {
        List<Branch> branches = branchRepository.findByStoreId(storeId);
        return branches.stream().map(BranchMapper::toDto).toList();
    }

    @Override
    public BranchDto getBranchById(Long id) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                () -> new Exception("branch not found!")
        );
        return BranchMapper.toDto(existing);
    }
}
