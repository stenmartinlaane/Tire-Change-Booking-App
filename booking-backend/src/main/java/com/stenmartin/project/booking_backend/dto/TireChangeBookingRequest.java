package com.stenmartin.project.booking_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TireChangeBookingRequest {
    private String tireWorkshopId;
    private String bookingId;
    private String contactInformation;
}
