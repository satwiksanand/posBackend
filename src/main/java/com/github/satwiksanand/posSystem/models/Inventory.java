package com.github.satwiksanand.posSystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    private LocalTime lastUpdate;

    @PreUpdate
    @PrePersist
    protected void onUpdate(){
        this.lastUpdate = LocalTime.now();
    }
}
