package com.github.satwiksanand.posSystem.mapper;

import com.github.satwiksanand.posSystem.models.Customer;
import com.github.satwiksanand.posSystem.payload.dto.CustomerDto;

public class CustomerMapper {
    public static CustomerDto toDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }

    public static Customer toEntity(CustomerDto customerDto){
        return Customer.builder()
                .id(customerDto.getId())
                .fullName(customerDto.getFullName())
                .email(customerDto.getEmail())
                .phone(customerDto.getPhone())
                .createdAt(customerDto.getCreatedAt())
                .updatedAt(customerDto.getUpdatedAt())
                .build();
    }
}
