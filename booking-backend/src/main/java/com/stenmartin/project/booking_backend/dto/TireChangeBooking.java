package com.stenmartin.project.booking_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public class TireChangeBooking {
    private String id;
    private String tireWorkshopName;
    private String tireWorkshopAddress;
    private ZonedDateTime dateTime;
}
