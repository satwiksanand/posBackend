package com.github.satwiksanand.posSystem.models;

import com.github.satwiksanand.posSystem.domain.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double totalAmount;
    private LocalDateTime createdAt;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private User cashier;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<OrderItem> items;

    private PaymentType paymentType;

    @PrePersist
    private void onCreate()
    {
        this.createdAt = LocalDateTime.now();
    }
}
