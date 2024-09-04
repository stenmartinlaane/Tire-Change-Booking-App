package com.stenmartin.project.booking_backend.dto.model;

import com.stenmartin.project.booking_backend.dto.entity.TireWorkshop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private T result;
    private String statusCode;
    private String message;
    private boolean success;
    private TireWorkshop tireWorkshop;
}
