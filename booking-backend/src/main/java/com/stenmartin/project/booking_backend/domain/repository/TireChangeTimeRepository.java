package com.stenmartin.project.booking_backend.domain.repository;

import com.stenmartin.project.booking_backend.domain.entity.TireChangeTime;
import com.stenmartin.project.booking_backend.domain.model.DomainResponse;
import com.stenmartin.project.booking_backend.domain.model.TireChangeSchedulingResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TireChangeTimeRepository {

    public CompletableFuture<DomainResponse<List<TireChangeTime>>> findAllAsync(String from, String to);
    public DomainResponse<TireChangeSchedulingResponse> scheduleTireChange(String tireChangeTimeId, String contactInformation);
    public String getWORKSHOP_ID();

}


