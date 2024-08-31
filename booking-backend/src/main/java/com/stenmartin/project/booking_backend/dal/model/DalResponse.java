package com.stenmartin.project.booking_backend.dal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DalResponse<T> {
    private T reult;
    private String statusCode;
    private String message;
    private boolean success;
}
