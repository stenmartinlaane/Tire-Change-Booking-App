package com.stenmartin.project.booking_backend.dal.model;


import com.stenmartin.project.booking_backend.dal.entity.TireWorkshop;
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
