package com.stenmartin.project.booking_backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DomainResponse<T> {
    private T reult;
    private String statusCode;
    private String message;
    private boolean success;
}
