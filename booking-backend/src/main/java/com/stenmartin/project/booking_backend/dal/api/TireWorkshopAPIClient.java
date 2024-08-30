package com.stenmartin.project.booking_backend.dal.api;

import com.stenmartin.project.booking_backend.dto.response.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.dto.interfaces.TireChangeTimesResponse;

import java.util.concurrent.CompletableFuture;

public interface TireWorkshopAPIClient {
    public CompletableFuture<TireChangeTimesResponse> getTireChangeTimesAsync(String from, String until);
    public TireChangeSchedulingResponse scheduleTireChange(String tireChangeBookingId, String contactInformation);
    public String getTireWorkshopId();
}
