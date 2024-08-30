package com.stenmartin.project.booking_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DomainResponse<T> {
    private T reult;
    private String statusCode;
    private String message;
    private boolean success;
}
