package com.stenmartin.project.booking_backend.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TireChangeSchedulingFilters {
    private String to;
    private String from;
}
