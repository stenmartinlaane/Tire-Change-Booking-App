package com.stenmartin.project.booking_backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public class TireChangeBooking {
    private String id;
    private TireWorkshop tireWorkshop;
    private ZonedDateTime dateTime;
}
