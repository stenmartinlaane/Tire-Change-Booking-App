package com.stenmartin.project.booking_backend.dal.model;

import com.stenmartin.project.booking_backend.dal.entity.TireWorkshop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DalResponse<T> {
    private T result;
    private String statusCode;
    private String message;
    private boolean success;
    private TireWorkshop tireWorkshop;
}
