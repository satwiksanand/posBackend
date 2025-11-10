package com.github.satwiksanand.posSystem.payload.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class BranchDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private List<String> workingDays;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private StoreDto store;
    private UserDto manager;
    private Long StoreId;
}
