package com.stenmartin.project.booking_backend.dto.response;

import com.stenmartin.project.booking_backend.dto.interfaces.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.dto.interfaces.TireChangeTimesResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements TireChangeTimesResponse,
        TireChangeSchedulingResponse
{
    private String code;
    private String message;

    @Override
    public boolean isSuccessful() {
        return false;
    }

    @Override
    public String GetStatusCode() {
        return code;
    }
}
