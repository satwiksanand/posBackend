package com.github.satwiksanand.posSystem.service.impl;

import com.github.satwiksanand.posSystem.configurations.SecurityConfig;
import com.github.satwiksanand.posSystem.domain.UserRole;
import com.github.satwiksanand.posSystem.mapper.UserMapper;
import com.github.satwiksanand.posSystem.models.Branch;
import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.UserDto;
import com.github.satwiksanand.posSystem.repository.BranchRepository;
import com.github.satwiksanand.posSystem.repository.StoreRepository;
import com.github.satwiksanand.posSystem.repository.UserRepository;
import com.github.satwiksanand.posSystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;

    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("store not found!")
        );
        Branch branch = null;
        if (employee.getRole() == UserRole.BRANCH_MANAGER) {
            if (employee.getBranchId() == null) {
                throw new Exception("branch id is required to create branch manager");
            }
            branch = branchRepository.findById(employee.getBranchId()).orElseThrow(
                    () -> new Exception("branch not found!")
            );
        }
        User user = UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(SecurityConfig.passwordEncoder().encode(employee.getPassword()));
        User savedEmployee = userRepository.save(user);
        if (employee.getRole() == UserRole.BRANCH_MANAGER && branch != null) {
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }
        return UserMapper.toDTO(savedEmployee);
    }

    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {
        Branch branch = branchRepository.findById(employee.getBranchId()).orElseThrow(
                () -> new Exception("branch not found!")
        );
        if (employee.getRole() == UserRole.BRANCH_CASHIER || employee.getRole() == UserRole.BRANCH_MANAGER) {
            User user = UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setPassword(SecurityConfig.passwordEncoder().encode(employee.getPassword()));
            return UserMapper.toDTO(userRepository.save(user));
        }
        throw new Exception("branch role not supported!");
    }

    @Override
    public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {
        User employee = userRepository.findById(employeeId).orElseThrow(
                () -> new Exception("employee does not exists")
        );

        Branch branch = branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(
                () -> new Exception("branch not found!")
        );
        employee.setEmail(employeeDetails.getEmail());
        employee.setFullname(employeeDetails.getFullname());
        employee.setPassword(SecurityConfig.passwordEncoder().encode(employeeDetails.getPassword()));
        employee.setRole(employeeDetails.getRole());
        employee.setBranch(branch);
        return userRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User employee = userRepository.findById(employeeId).orElseThrow(
                () -> new Exception("employee does not exists")
        );
        userRepository.delete(employee);
    }

    @Override
    public List<UserDto> findStoreEmployees(Long storeId, UserRole userRole) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("store not found!")
        );
        return userRepository.findByStore(store).stream().filter(user -> userRole == null || user.getRole() == userRole).map(UserMapper::toDTO).toList();
    }

    @Override
    public List<UserDto> findBranchEmployees(Long branchId, UserRole userRole) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(
                () -> new Exception("branch not found!")
        );
        return userRepository.findByBranchId(branchId).stream().filter(user -> userRole == null || user.getRole() == userRole).map(UserMapper::toDTO).toList();
    }
}
