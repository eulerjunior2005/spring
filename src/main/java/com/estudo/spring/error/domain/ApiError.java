package com.estudo.spring.error.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiError {
    private final String code;
    private final String message;
    private final String[] args;
}
