package com.stenmartin.project.booking_backend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TireChangeBookingsFilters {
    private String to;
    private String from;
}
