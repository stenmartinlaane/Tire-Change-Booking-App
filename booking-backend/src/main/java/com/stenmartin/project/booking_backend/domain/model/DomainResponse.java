package com.stenmartin.project.booking_backend.domain.model;

import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomainResponse<T> {
    private T result;
    private String statusCode;
    private String message;
    private boolean success;
    private TireWorkshop tireWorkshop;
}
