package com.github.satwiksanand.posSystem.payload.dto;

import com.github.satwiksanand.posSystem.domain.PaymentType;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class OrderItemDto {
    private Long id;
    private Integer quantity;
    private Double price;
    private ProductDto product;
    private Long productId;
    private Long orderId;
    private PaymentType paymentType;
}
