package com.github.satwiksanand.posSystem.mapper;

import com.github.satwiksanand.posSystem.models.OrderItem;
import com.github.satwiksanand.posSystem.payload.dto.OrderItemDto;

public class OrderItemMapper {
    public static OrderItemDto toDto(OrderItem orderItem){
        if(orderItem == null){
            return null;
        }
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .product(ProductMapper.toDto(orderItem.getProduct()))
                .orderId(orderItem.getId())
                .build();
    }
}
