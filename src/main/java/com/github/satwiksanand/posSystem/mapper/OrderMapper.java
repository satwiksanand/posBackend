package com.github.satwiksanand.posSystem.mapper;

import com.github.satwiksanand.posSystem.models.Order;
import com.github.satwiksanand.posSystem.payload.dto.OrderDto;

public class OrderMapper {
    public static OrderDto toDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .branchId(order.getBranch().getId())
                .cashier(UserMapper.toDTO(order.getCashier()))
                .customer(order.getCustomer())
                .paymentType(order.getPaymentType())
                .createdAt(order.getCreatedAt())
                .items(order.getItems().stream().map(OrderItemMapper::toDto).toList())
                .build();
    }
}
