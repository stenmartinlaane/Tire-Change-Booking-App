package com.stenmartin.project.booking_backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TireChangeTime {
    private String id;
    private TireWorkshop tireWorkshop;
    private ZonedDateTime dateTime;
}
