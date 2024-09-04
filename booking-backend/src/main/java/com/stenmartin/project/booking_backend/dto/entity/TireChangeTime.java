package com.stenmartin.project.booking_backend.dto.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TireChangeTime {
    private String id;
    private ZonedDateTime dateTime;
}
