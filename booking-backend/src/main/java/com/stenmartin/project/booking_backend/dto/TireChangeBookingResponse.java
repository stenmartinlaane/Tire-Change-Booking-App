package com.stenmartin.project.booking_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TireChangeBookingResponse {
    private String error;
    private String time;
    private String bookingId;
}