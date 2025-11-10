package com.github.satwiksanand.posSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    private String phone;

    @Email(message = "email is not valid")
    private String email;

    @ElementCollection
    private List<String> workingDays;

    private LocalTime openTime;
    private LocalTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private Store store;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User manager;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
