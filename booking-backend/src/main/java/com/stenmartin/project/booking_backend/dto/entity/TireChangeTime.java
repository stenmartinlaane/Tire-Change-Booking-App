package com.stenmartin.project.booking_backend.dto.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
public class TireChangeTime {
    private String id;
    private TireWorkshop tireWorkshop;
    private ZonedDateTime dateTime;
}
