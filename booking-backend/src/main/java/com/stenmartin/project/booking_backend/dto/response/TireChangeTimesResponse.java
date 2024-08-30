package com.stenmartin.project.booking_backend.dto.response;


import com.stenmartin.project.booking_backend.dto.entity.TireChangeTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class TireChangeTimesResponse implements com.stenmartin.project.booking_backend.dto.interfaces.TireChangeTimesResponse {
    private String code;

    @Getter
    private List<TireChangeTime> tireChangeTimes;

    @Override
    public boolean isSuccessful() {
        return true;
    }

    @Override
    public String GetStatusCode() {
        return code;
    }
}
