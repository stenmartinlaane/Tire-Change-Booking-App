package com.stenmartin.project.booking_backend.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private T reult;
    private String statusCode;
    private String message;
    private boolean success;
}
