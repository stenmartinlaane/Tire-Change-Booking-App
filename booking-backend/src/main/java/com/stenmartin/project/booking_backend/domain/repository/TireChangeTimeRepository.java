package com.stenmartin.project.booking_backend.domain.repository;

import com.stenmartin.project.booking_backend.dto.response.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.dto.interfaces.TireChangeTimesResponse;

import java.util.concurrent.CompletableFuture;

public interface TireChangeTimeRepository {

    public CompletableFuture<TireChangeTimesResponse> findAllAsync(String from, String to);
    public TireChangeSchedulingResponse scheduleTireChange(String tireChangeBookingId, String contactInformation);
    public String getTireWorkshopId();
}


