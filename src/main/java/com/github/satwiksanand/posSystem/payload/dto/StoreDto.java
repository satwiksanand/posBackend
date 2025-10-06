package com.github.satwiksanand.posSystem.payload.dto;

import com.github.satwiksanand.posSystem.domain.StoreStatus;
import com.github.satwiksanand.posSystem.models.StoreContact;
import jakarta.persistence.Embedded;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StoreDto {
    private Long id;

    private String brand;

    private UserDto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    @Embedded
    private StoreContact contact;
}
