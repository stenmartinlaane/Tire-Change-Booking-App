package com.stenmartin.project.booking_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TireChangeSchedulingRequest {
    private String tireWorkshopId;
    private String bookingId;
    private String contactInformation;
}
