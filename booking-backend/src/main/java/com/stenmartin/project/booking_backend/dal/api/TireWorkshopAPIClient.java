package com.stenmartin.project.booking_backend.dal.api;

import com.stenmartin.project.booking_backend.dal.model.DalResponse;
import com.stenmartin.project.booking_backend.dal.entity.TireChangeTime;
import com.stenmartin.project.booking_backend.dal.model.TireChangeSchedulingResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TireWorkshopAPIClient {
    public CompletableFuture<DalResponse<List<TireChangeTime>>> getTireChangeTimesAsync(String from, String until);
    public DalResponse<TireChangeSchedulingResponse> scheduleTireChange(String tireChangeBookingId, String contactInformation);
    public String getWORKSHOP_ID();
}
