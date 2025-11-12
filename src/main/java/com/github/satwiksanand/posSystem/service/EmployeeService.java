package com.github.satwiksanand.posSystem.service;

import com.github.satwiksanand.posSystem.domain.UserRole;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.UserDto;

import java.util.List;

public interface EmployeeService {
    UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception;
    UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception;
    User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<UserDto> findStoreEmployees(Long storeId, UserRole userRole) throws Exception;
    List<UserDto> findBranchEmployees(Long branchId, UserRole userRole) throws Exception;

}
