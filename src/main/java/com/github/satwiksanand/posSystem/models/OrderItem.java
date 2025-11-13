package com.github.satwiksanand.posSystem.models;

import com.github.satwiksanand.posSystem.domain.PaymentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer quantity;
    private Double price;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    private PaymentType paymentType;
}
