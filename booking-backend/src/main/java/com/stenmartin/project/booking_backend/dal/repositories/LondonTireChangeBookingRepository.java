package com.stenmartin.project.booking_backend.dal.repositories;

import com.stenmartin.project.booking_backend.dal.api.LondonTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.domain.repository.TireChangeTimeRepository;
import com.stenmartin.project.booking_backend.dto.response.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.dto.interfaces.TireChangeTimesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public class LondonTireChangeBookingRepository implements TireChangeTimeRepository {

    private final LondonTireWorkshopAPIClient tireWorkshopAPIClient;

    @Autowired
    public LondonTireChangeBookingRepository(LondonTireWorkshopAPIClient tireWorkshopAPIClient) {
        this.tireWorkshopAPIClient = tireWorkshopAPIClient;
    }

    @Override
    public CompletableFuture<TireChangeTimesResponse> findAllAsync(String from, String to) {
        return tireWorkshopAPIClient.getTireChangeTimesAsync(from, to);
    }

    @Override
    public TireChangeSchedulingResponse scheduleTireChange(String tireChangeBookingId, String contactInformation) {
        return tireWorkshopAPIClient.scheduleTireChange(tireChangeBookingId, contactInformation);
    }

    @Override
    public String getTireWorkshopId() {
        return tireWorkshopAPIClient.getTireWorkshopId();
    }

}
