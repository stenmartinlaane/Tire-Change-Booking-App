package com.stenmartin.project.booking_backend.dal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DalResponse<T> {
    private T reult;
    private String statusCode;
    private String message;
    private boolean success;
}
