package com.github.satwiksanand.posSystem.payload.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
    private String message;
    private int statusCode;
}
