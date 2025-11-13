package com.github.satwiksanand.posSystem.payload.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

//i am not really using it, but maybe i will in the future.

@Builder
@Data
public class CustomerDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
