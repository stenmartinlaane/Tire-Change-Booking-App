package com.stenmartin.project.booking_backend.domain.model;

import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TireChangeSchedulingResponse {
    private String time;
    private String bookingId;
    private TireWorkshop tireWorkshop;
}
