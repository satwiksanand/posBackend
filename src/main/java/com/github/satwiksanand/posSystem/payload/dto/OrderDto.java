package com.github.satwiksanand.posSystem.payload.dto;

import com.github.satwiksanand.posSystem.domain.PaymentType;
import com.github.satwiksanand.posSystem.models.Customer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class OrderDto {
    private Long id;
    private Double totalAmount;
    private LocalDateTime createdAt;
    private BranchDto branch;
    private UserDto cashier;
    private Customer customer;
    private Long branchId;
    private Long customerId;
    private List<OrderItemDto> items;
    private PaymentType paymentType;
}
