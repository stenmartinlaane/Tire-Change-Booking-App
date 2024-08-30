package com.stenmartin.project.booking_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TireChangeSchedulingResponse implements com.stenmartin.project.booking_backend.dto.interfaces.TireChangeSchedulingResponse {
    private String error;
    private String code;

    private String time;
    private String bookingId;
    private String workshopName;
    private String workshopId;
    private String workshopAddress;
    private Set<String> supportedVehicleTypes;


    @Override
    public boolean isSuccessful() {
        return true;
    }

    @Override
    public String GetStatusCode() {
        return code;
    }
}
