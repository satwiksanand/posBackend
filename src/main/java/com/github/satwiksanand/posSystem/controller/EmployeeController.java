package com.github.satwiksanand.posSystem.controller;

import com.github.satwiksanand.posSystem.domain.UserRole;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.UserDto;
import com.github.satwiksanand.posSystem.payload.response.ApiResponse;
import com.github.satwiksanand.posSystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto> createStoreEmployee(@RequestBody UserDto userDto, @PathVariable Long storeId) throws Exception {
        return ResponseEntity.ok(employeeService.createStoreEmployee(userDto, storeId));
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto> createBranchEmployee(@RequestBody UserDto userDto, @PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(employeeService.createBranchEmployee(userDto, branchId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateEmployee(@RequestBody UserDto userDto, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(employeeService.updateEmployee(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) throws Exception {
        employeeService.deleteEmployee(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("employee deleted!");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<UserDto>> findStoreEmployee(@PathVariable Long id, @RequestParam(required = false)UserRole userRole) throws Exception {
        return ResponseEntity.ok(employeeService.findStoreEmployees(id, userRole));
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<UserDto>> findBranchEmployee(@PathVariable Long id, @RequestParam(required = false)UserRole userRole) throws Exception {
        return ResponseEntity.ok(employeeService.findBranchEmployees(id, userRole));
    }
}
